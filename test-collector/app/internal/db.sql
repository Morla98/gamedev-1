-- Register myself in the main schemas collector table
INSERT INTO main.collectors
  ("id", "name", "last_seen")
VALUES
  ('template-collector', 'This is a template for a collector', NOW())
ON CONFLICT (id) DO -- if collector already exists
  UPDATE SET "last_seen" = NOW()
;

-- Register my achievements in the main schemas achievement table
INSERT INTO main.achievements
  ("id", "collector_id", "name", "description", "value")
VALUES
  ('jira-noob', 'template-collector', 'JIRA Noob', 'Close at least one JIRA ticket', 1)
ON CONFLICT ("id", "collector_id") DO NOTHING
;
-- ...


-- Create my own metrics table
CREATE TABLE IF NOT EXISTS metrics."template_collector" (
    "id" SERIAL NOT NULL PRIMARY KEY,
    "user" TEXT NOT NULL,
    "action" VARCHAR(50) NOT NULL,
    "jira_key" TEXT NOT NULL,
    "timestamp" NUMERIC NOT NULL
);
