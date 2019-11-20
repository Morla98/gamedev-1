--
-- Storage of the main application
--
CREATE SCHEMA "main";

-- TODO: Benutzer "main" erstellen und zum Owner des Schemas machen?

-- TODO: Tabelle users verbessern
CREATE TABLE main."users" (
  "email" TEXT PRIMARY KEY,
  "name" TEXT NOT NULL,
  "anon" BOOLEAN NOT NULL,
  "level" NUMERIC NOT NULL,
  "score" NUMERIC NOT NULL
);
-- TODO: Tabelle achievements verbessern
CREATE TABLE main."achievements" (
  "id" serial NOT NULL
);
CREATE TABLE main."collectors" (
	"id" numeric NOT NULL ,
 	"email" TEXT NOT NULL references main.users(email) ,
  	"name" TEXT,
  	PRIMARY KEY (id,email)	
);
CREATE TABLE main."nametable"(
	"id" numeric PRIMARY KEY,
	"name" TEXT NOT NULL unique
	);


