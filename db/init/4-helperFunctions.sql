--get Next available Collector ID

create function main.getNextID() returns numeric as $$
declare 
	f_id numeric;
begin
	SELECT count(main.nametable.id)
	into strict f_id
	FROM main.nametable;
	return f_id;
end;
$$ LANGUAGE plpgsql;

--erstelle Collector klasse 
-- Funktion zum Registrieren/Updaten eines Konnektors.
-- Wird von den Konnektoren selbst aufgerufen.

create function main.Create_Collector(collector_name TEXT, coll_id numeric) returns void as $$
Begin
   IF EXISTS (
	   SELECT * FROM main.collectors WHERE main.collectors.name = collector_name
   ) THEN raise exception 'Collector already exists';
   else 
   EXECUTE 'create table main.' || collector_name || ' (ID numeric, email Text, name Text , Foreign Key (ID,email) references main.collectors(ID,email),primary key(ID,email))';
   INSERT INTO main.nametable (id,name) values (coll_id,collector_name);
   IF EXISTS 
   	(SELECT * from main.users)
   		THEN
   			EXECUTE 'INSERT INTO main.collectors (ID,email) Select '||coll_id||' as "id",email as "name" from main.users';
   			EXECUTE 'INSERT INTO main.' || collector_name || ' (ID,email)  Select '||coll_id||' as "id",email from main.users';
   		END IF;
	END IF;
END;
$$ LANGUAGE plpgsql;

--get Collector Name by Collector ID

create function main.getNameByID(id numeric) returns TEXT as $$
declare
	f_name TEXT;
begin
	SELECT name
	INTO strict f_name
	FROM main.nametable
	where main.nametable.id = id;
	IF not FOUND then raise exception 'ID doesnt exist';
	END IF;
	return f_name;
END;
$$ LANGUAGE plpgsql;

