--
-- Storage of the main application
--
CREATE SCHEMA "main";

-- TODO: Benutzer "main" erstellen und zum Owner des Schemas machen?

-- TODO: Tabelle users verbessern
CREATE TABLE main."users" (
  "id" serial NOT NULL,
  "name" TEXT NOT NULL,
  "ldap" TEXT NOT NULL
);

-- TODO: Tabelle achievements verbessern
CREATE TABLE main."achievements" (
  "id" serial NOT NULL
);

-- TODO: Tabelle connectors verbessern
CREATE TABLE main."connectors" (
  "name" TEXT NOT NULL PRIMARY KEY,
  "last_seen" TIMESTAMPTZ NOT NULL
);

-- Funktion zum Registrieren/Updaten eines Konnektors.
-- Wird von den Konnektoren selbst aufgerufen.
CREATE FUNCTION main."UPDATE_CONNECTOR" (connector_name main.connectors.name%TYPE)
RETURNS VOID
AS $$
BEGIN
   -- Check if connector is already known
   IF EXISTS (
	   SELECT *
	   FROM main.connectors
	   WHERE main.connectors.name = connector_name
   ) THEN
      -- raise notice 'Connector known, updating.';
	  UPDATE main.connectors SET last_seen = NOW() WHERE main.connectors.name = connector_name;
   ELSE
      -- raise notice 'Connector unknown, inserting';
	  INSERT INTO main.connectors (name, last_seen) VALUES(connector_name, NOW());
   END IF;
END;
$$ LANGUAGE plpgsql;
