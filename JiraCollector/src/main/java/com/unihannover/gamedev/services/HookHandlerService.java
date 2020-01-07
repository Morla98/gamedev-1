package com.unihannover.gamedev.services;

import com.google.gson.*;
import com.unihannover.gamedev.models.Configuration;
import com.unihannover.gamedev.models.Metric;
import com.unihannover.gamedev.repositories.MetricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HookHandlerService {

    @Autowired
    private MetricRepository metricRepository;

    @Autowired
    private UserAchievementUpdaterService userAchievementUpdaterService;

    @Autowired
    private ConfigurationService configurationService;

    /**
     * Processes a JIRA webhook object
     */
    public void handle(String data) {
        // Try to parse the JSON data into an object
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(data, JsonObject.class);

        // Extract event type ...
        String eventType = jsonObject.getAsJsonPrimitive("webhookEvent").getAsString();

        // ... and call specific handler
        switch (eventType) {
            case "jira:issue_created":
                this.handleIssueCreated(jsonObject);
                break;
            case "jira:issue_deleted":
                this.handleIssueDeleted(jsonObject);
                break;
            case "jira:issue_updated":
                this.handleIssueUpdated(jsonObject);
                break;
            default:
                System.out.printf("Unsupported event type: '%s'!\n", eventType);
                return;
        }

        /* Call user achievement updater for the current user
         * TODO: Think about the overhead here, maybe do not call
         */
        if (jsonObject.has("user")) {
            String userEmail = jsonObject.getAsJsonObject("user").getAsJsonPrimitive("emailAddress").getAsString();

            userAchievementUpdaterService.updateForUser(userEmail, false);
        }
    }

    /**
     * Handles a created issue
     */
    private void handleIssueCreated(JsonObject jsonObject) {

        // Extract required information
        String eventType = jsonObject.getAsJsonPrimitive("webhookEvent").getAsString();

        String userEmail = jsonObject.getAsJsonObject("user").getAsJsonPrimitive("emailAddress").getAsString();
        String userKey = jsonObject.getAsJsonObject("user").getAsJsonPrimitive("key").getAsString();
        String issueKey = jsonObject.getAsJsonObject("issue").getAsJsonPrimitive("key").getAsString();
        String issueType = getIssueTypeHelper(jsonObject);
        String timestamp = jsonObject.getAsJsonPrimitive("timestamp").getAsString();

        // TODO: Remove debug output
        System.out.printf("User %s (%s) triggered event %s on issue %s at %s.\n", userEmail, userKey, eventType, issueKey, timestamp);

        this.insertMetric(userEmail, issueKey, issueType, Metric.EVENT_ISSUE_CREATED, Metric.ACTION_NONE, timestamp);
    }

    /**
     * Handles a deleted issue
     */
    private void handleIssueDeleted(JsonObject jsonObject) {

        // Extract required information
        String eventType = jsonObject.getAsJsonPrimitive("webhookEvent").getAsString();

        String userEmail = jsonObject.getAsJsonObject("user").getAsJsonPrimitive("emailAddress").getAsString();
        String userKey = jsonObject.getAsJsonObject("user").getAsJsonPrimitive("key").getAsString();
        String issueKey = jsonObject.getAsJsonObject("issue").getAsJsonPrimitive("key").getAsString();
        String issueType = getIssueTypeHelper(jsonObject);
        String timestamp = jsonObject.getAsJsonPrimitive("timestamp").getAsString();

        // TODO: Remove debug output
        System.out.printf("User %s (%s) triggered event %s on issue %s at %s.\n", userEmail, userKey, eventType, issueKey, timestamp);

        this.insertMetric(userEmail, issueKey, issueType, Metric.EVENT_ISSUE_DELETED, Metric.ACTION_NONE, timestamp);
    }

    /**
     * Handles an updated issue
     */
    private void handleIssueUpdated(JsonObject jsonObject) {

        // Extract required information
        String eventType = jsonObject.getAsJsonPrimitive("webhookEvent").getAsString();
        String issueEventType = jsonObject.getAsJsonPrimitive("issue_event_type_name").getAsString();

        String userEmail = jsonObject.getAsJsonObject("user").getAsJsonPrimitive("emailAddress").getAsString();
        String userKey = jsonObject.getAsJsonObject("user").getAsJsonPrimitive("key").getAsString();
        String issueKey = jsonObject.getAsJsonObject("issue").getAsJsonPrimitive("key").getAsString();
        String issueType = getIssueTypeHelper(jsonObject);
        String timestamp = jsonObject.getAsJsonPrimitive("timestamp").getAsString();

        // TODO: Remove debug output
        System.out.printf("User %s (%s) triggered event %s (exactly: %s) on issue %s at %s.\n", userEmail, userKey, eventType, issueEventType, issueKey, timestamp);

        // Handling of specialized issueEventTypes
        if (issueEventType.equals("issue_commented")) { // User commented on issue
            this.insertMetric(userEmail, issueKey, issueType, Metric.EVENT_ISSUE_UPDATED, Metric.ACTION_ISSUE_COMMENTED, timestamp);
            return;
        } else if (issueEventType.equals("issue_comment_edited")) { // User edited comment on issue
            this.insertMetric(userEmail, issueKey, issueType, Metric.EVENT_ISSUE_UPDATED, Metric.ACTION_ISSUE_COMMENT_EDITED, timestamp);
            return;
        }

        // Check if changelog set exists
        if (!jsonObject.has("changelog")) {
            return;
        }

        // Get changelog items ...
        JsonArray changelogItems = jsonObject.getAsJsonObject("changelog").getAsJsonArray("items");

        // ... and iterate over them
        for (JsonElement changelogEntry : changelogItems) {

            // Extract properties (null-aware)
            String field = getStringValueHelper(changelogEntry.getAsJsonObject(), "field");
            String from = getStringValueHelper(changelogEntry.getAsJsonObject(), "from");
            String fromString = getStringValueHelper(changelogEntry.getAsJsonObject(), "fromString");
            String to = getStringValueHelper(changelogEntry.getAsJsonObject(), "to");
            String toString = getStringValueHelper(changelogEntry.getAsJsonObject(), "toString");

            // TODO: Remove debug output
            System.out.printf("Field '%s' was updated from '%s' ('%s') to '%s' ('%s').\n", field, from, fromString, to, toString);

            // Issue status is now "Done"
            if (field.equals("status") && !fromString.equals("Done") && toString.equals("Done")) {
                this.insertMetric(userEmail, issueKey, issueType, Metric.EVENT_ISSUE_UPDATED, Metric.ACTION_ISSUE_DONE, timestamp);
                continue;
            }

            // Issue assigned to me (from someone else)
            if (field.equals("assignee") && !from.equals(userKey) && to.equals(userKey)) {
                this.insertMetric(userEmail, issueKey, issueType, Metric.EVENT_ISSUE_UPDATED, Metric.ACTION_ISSUE_ASSIGNED_TO_ME, timestamp);
                continue;
            }

            // Issue assigned away (from me)
            if (field.equals("assignee") && from.equals(userKey) && !to.equals(userKey)) {
                this.insertMetric(userEmail, issueKey, issueType, Metric.EVENT_ISSUE_UPDATED, Metric.ACTION_ISSUE_ASSIGNED_AWAY, timestamp);
                continue;
            }
        }
    }

    /**
     * Helper function for getting a JSONObjects property value that treats `null` as empty string
     */
    private String getStringValueHelper(JsonObject obj, String propertyName) {
        JsonElement temp = obj.get(propertyName);

        return (temp instanceof JsonNull) ? "" : temp.getAsString();
    }

    /**
     * Determines the internal issue type.
     * Since issue types like "Epic", "Story", etc. are not part of every Jira project and configurable by the user,
     * we have to look up the issue-type-ids in the configuration.
     */
    private String getIssueTypeHelper(JsonObject jsonObject) {

        // Get the configuration
        Configuration config = configurationService.getConfig();

        // Get the issue type id
        String issueTypeId = jsonObject.getAsJsonObject("issue").getAsJsonObject("fields").getAsJsonObject("issuetype").getAsJsonPrimitive("id").getAsString();

        if (issueTypeId.equals(config.getJiraIssueTypeIdEpic())) {
            return Metric.ISSUE_TYPE_EPIC;
        }

        if (issueTypeId.equals(config.getJiraIssueTypeIdStory())) {
            return Metric.ISSUE_TYPE_STORY;
        }

        if (issueTypeId.equals(config.getJiraIssueTypeIdSubtask())) {
            return Metric.ISSUE_TYPE_SUBTASK;
        }

        return Metric.ISSUE_TYPE_UNKNOWN;
    }

    /**
     * Insert a new metric entry into the metrics repository/database
     */
    private void insertMetric(String userEmail, String issueKey, String issueType, String eventType, String action, String timestamp) {

        // Create new model
        Metric metric = new Metric(userEmail, issueKey, issueType, eventType, action, timestamp);

        // Persist model in repository
        this.metricRepository.save(metric);

        // TODO: Remove debug output
        System.out.println("Persisted new metric entry.");
    }
}
