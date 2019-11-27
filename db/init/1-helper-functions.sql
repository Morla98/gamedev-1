CREATE OR REPLACE FUNCTION random_token (lenght int) 
returns TEXT 
AS $$
BEGIN
	return (select substr(md5(random()::text), 0, 25));
END;
$$LANGUAGE plpgsql;