<?php
namespace DevGame\TestConnector\Controller;

class JiraHookController
{
    private const ACTION_ISSUE_CLOSED = 'issue-closed';
    private const ACTION_ASSIGNED_AWAY = 'assigned-away';
    private const ACTION_ASSIGNED_TO_ME = 'assigned-to-me';

    protected $dbHandle;

    public function __construct($dbHandle)
    {
        $this->dbHandle = $dbHandle;
    }

    /**
     * Hook receive function
     */
    public function main(): void
    {
        try {
            // TODO: Validate the token

            // Read input data
            $json = file_get_contents('php://input');
            $jsonObj = @json_decode($json);
            if (null === $jsonObj) {
                throw new \RuntimeException('Error de-serializing the given JSON data.');
            }

            // Compile the prepared metrics insert query
            @pg_prepare($this->dbHandle,"insert-metrics-query", 'INSERT INTO metrics.template_collector ("user", "action", "jira_key", "timestamp") VALUES ($1, $2, $3, $4);');

            // Progress the hook object
            $this->progressHookObject($jsonObj);

        } catch(\Exception $e) {
            error_log($e->getMessage());
            exit(1);
        }
    }

    protected function progressHookObject($jsonObj): void
    {
        // Extract event type
        $eventType = $jsonObj->{"webhookEvent"};
        if (null == $eventType) {
            throw new \RuntimeException("Error extracting the event type.");
        }
        if ("jira:issue_updated" !== $eventType) {
            return;
        }

        // Extract user email
        $userMail = $jsonObj->{"user"}->{"emailAddress"};
        if (null === $userMail) {
            throw new \RuntimeException("Error extracting the user email.");
        }

        $userKey = $jsonObj->{"user"}->{"key"};
        if (null == $userKey) {
            throw new \RuntimeException("Error extracting the user key.");
        }

        // Extract issue reference
        $issueKey = $jsonObj->{"issue"}->{"key"};
        if (null == $issueKey) {
            throw new \RuntimeException("Error extracting the issue key.");
        }

        // Extract timestamp
        $timestamp = (int) $jsonObj->{"timestamp"};
        if (!is_numeric($timestamp)) {
            throw new \RuntimeException("Error extracting the timestamp.");
        }

        // Extract changelog items
        $changelogItems = $jsonObj->{"changelog"}->{"items"};
        if (!is_array($changelogItems)) {
            throw new \RuntimeException("Error extracting the changelog items.");
        }

        // Iterate over changelog items
        foreach($changelogItems as $changelogItem) {
            // Issue closed
            if ($changelogItem->{"field"} == "status" && $changelogItem->{"fieldtype"} == "jira" && $changelogItem->{"toString"} == 'Done') {
                $this->insertMetric($userMail, self::ACTION_ISSUE_CLOSED, $issueKey, $timestamp);
                continue;
            }
            // Issue assigned away
            if ($changelogItem->{"field"} == "assignee" && $changelogItem->{"fieldtype"} == "jira" && $changelogItem->{"from"} == $userKey && $changelogItem->{"to"} !== $userKey) {
                $this->insertMetric($userMail, self::ACTION_ASSIGNED_AWAY, $issueKey, $timestamp);
            }
            // Issue assigned to myself
            if ($changelogItem->{"field"} == "assignee" && $changelogItem->{"fieldtype"} == "jira" && $changelogItem->{"from"} !== $userKey && $changelogItem->{"to"} == $userKey) {
                $this->insertMetric($userMail, self::ACTION_ASSIGNED_TO_ME, $issueKey, $timestamp);
            }
            // TODO: Add more supported event types
        }
    }

    protected function insertMetric(string $userMail, string $action, string $jiraKey, string $timestamp): void {
        $result = @pg_execute($this->dbHandle, "insert-metrics-query", [$userMail, $action, $jiraKey, $timestamp]);
    }
}
