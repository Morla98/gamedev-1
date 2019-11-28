CREATE OR REPLACE FUNCTION random_token (length int) 
returns TEXT 
AS $$
BEGIN
	return (select substr(md5(random()::text), 0, length));
END;
$$LANGUAGE plpgsql;