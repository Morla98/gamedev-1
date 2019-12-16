<?php
namespace DevGame\TestConnector\Logic;

class MyJob implements AchievementLogicInterface
{
    /**
     * @inheritDoc
     */
    public static function generateOrUpdate($dbHandle): int
    {
        $query = <<<EOF
INSERT INTO main.user_achievements ("collector_id", "achievement_id", "user_email", "progress", "last_updated")
(SELECT
 'template-collector' as "collector_id",
 'my-job' as "achievement_id",
 u.email as "user_email",
 (CASE WHEN COUNT(m.id) >= 10 THEN 10 ELSE COUNT(m.id) END) * 10 AS "progress",
 NOW() as "last_updated"
FROM main.users u
LEFT JOIN (SELECT DISTINCT "id", "user", "timestamp" FROM metrics.template_collector WHERE action='assigned-to-me') m ON (u.email=m.user)
GROUP BY(u.email))
ON CONFLICT ("achievement_id", "collector_id", "user_email") DO UPDATE SET "last_updated"= NOW(), "progress" = EXCLUDED.progress
;
EOF;

        $result = @pg_query($dbHandle, $query);
        if (!$result) {
            throw new \RuntimeException(sprintf('Error updating user-achievements for achievement %s. : %s', self::class, pg_last_error($dbHandle)));
        }

        return (int) pg_affected_rows($result);
    }
}
