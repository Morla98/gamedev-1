--
-- Storage of the main application
--
CREATE SCHEMA "main";

-- TODO: Benutzer "main" erstellen und zum Owner des Schemas machen?

-- Table managing the users
CREATE TABLE main.users (
	email TEXT NOT NULL unique PRIMARY KEY,
	UID SERIAL NOT NULL,
	userName TEXT NOT NULL,
	anonymous BOOLEAN NOT NULL default FALSE,
	level NUMERIC NOT NULL default 0,
	score NUMERIC NOT NULL default 0,
	fullName TEXT NOT NULL
);

--TABLE managing Collectors

CREATE TABLE main.collector(
	CID SERIAL unique PRIMARY KEY, 	 
  	name TEXT NOT NULL
);

-- TABLE managing achievement
-- one achievement is only identifible in combination with Collector ID

CREATE TABLE main.achievements(
	AID NUMERIC NOT NULL,
	CID SERIAL NOT NULL references main.collector(CID),
	description TEXT NOT NULL,
	name TEXT NOT NULL,
	value NUMERIC NOT NULL,
	PRIMARY KEY (AID,CID)
);

-- TABLE managing the Achievements of specific User,
 
CREATE TABLE main.UserAchievement(
	AID NUMERIC,
	CID SERIAL,
	email TEXT references main.users(email),
	progress numeric default 0,
	last_updated TIMESTAMPTZ,
	FOREIGN KEY (AID,CID) references main.achievements(AID,CID),
	primary key (AID,CID,email)
);