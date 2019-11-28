--
-- Storage of the main application
--
CREATE SCHEMA "main";

-- TODO: Benutzer "main" erstellen und zum Owner des Schemas machen?

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
	"token" TEXT NOT NULL UNIQUE DEFAULT random_token(15), 
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