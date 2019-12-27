--
-- Storage of the main application
--
CREATE SCHEMA "main";

-- Table managing the users
CREATE TABLE main."users" (
    "email" TEXT NOT NULL,
    "uid" TEXT NOT NULL,
    "username" TEXT NOT NULL,
    "fullname" TEXT NOT NULL,
    "anonymous" BOOLEAN NOT NULL DEFAULT TRUE,
    "level" NUMERIC NOT NULL DEFAULT 0,
    "score" NUMERIC NOT NULL DEFAULT 0,
    PRIMARY KEY ("email")
);

--Table managing Collectors
CREATE TABLE main."collectors" (
    "id" TEXT NOT NULL,
    "name" TEXT NOT NULL,
    "last_seen" TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    "token" TEXT NOT NULL DEFAULT random_token(256),
    PRIMARY KEY("id")
);

-- Table managing achievement
--  (one achievement is only identifiable in combination with its collectors id)
CREATE TABLE main."achievements" (
    "id" TEXT NOT NULL,
    "collector_id" TEXT NOT NULL REFERENCES main.collectors("id"),
    "name" TEXT NOT NULL,
    "description" TEXT NOT NULL,
    "value" NUMERIC NOT NULL,
    PRIMARY KEY ("id","collector_id")
);

-- Table managing the achievements of specific user
CREATE TABLE main."user_achievements" (
    "achievement_id" TEXT NOT NULL,
    "collector_id" TEXT NOT NULL,
    "user_email" TEXT NOT NULL,
    "progress" NUMERIC DEFAULT 0,
    "last_updated" TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    FOREIGN KEY ("achievement_id","collector_id") REFERENCES main.achievements("id","collector_id"),
    FOREIGN KEY ("user_email") REFERENCES main.users("email"),
    PRIMARY KEY ("achievement_id","collector_id","user_email")
);

-- Function for updating users scores
CREATE OR REPLACE FUNCTION main.update_user_scores()
RETURNS trigger AS
$$
BEGIN
    -- Update every users score attribute as the sum of every user_achievement's value that was achieved by that user
    UPDATE main.users u
    SET score = (
	    SELECT COALESCE(SUM(a.value), 0)
	    FROM main.user_achievements ua
	    JOIN main.achievements a ON(ua.achievement_id=a.id AND ua.collector_id=a.collector_id)
	    WHERE
	        ua.progress >= 100
	       AND ua.user_email=u.email
    );

   RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Add trigger to user_achievements table
CREATE TRIGGER changed AFTER INSERT OR UPDATE OR DELETE OR TRUNCATE ON main.user_achievements FOR STATEMENT EXECUTE PROCEDURE main.update_user_scores();
