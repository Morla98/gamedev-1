CREATE OR REPLACE FUNCTION random_token (length_rnd int) 
returns TEXT 
AS $$
declare 
counter numeric := 0;
amount integer := length_rnd / 32;
result_token TEXT default '';
BEGIN
	While (counter <= amount) LOOP
	result_token := result_token || '' || (select md5(random()::text));
	counter := counter + 1;
	END LOOP;
	return substr(result_token, 0, length_rnd + 1);
END;
$$LANGUAGE plpgsql;