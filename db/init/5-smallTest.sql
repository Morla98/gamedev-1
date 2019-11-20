--create Users
INSERT INTO main.users (email,name,anon,level,score) VALUES ('abc@gmx.de', 'abc', FALSE, 3, 300);
INSERT INTO main.users (email,name,anon,level,score) VALUES ('abc1@gmx.de', 'abc', TRUE, 4, 450);
INSERT INTO main.users (email,name,anon,level,score) VALUES ('abc2@gmx.de', 'abc2', TRUE, 3, 350);
INSERT INTO main.users (email,name,anon,level,score) VALUES ('abc3@gmx.de', 'abc3', FALSE, 5, 500);
INSERT INTO main.users (email,name,anon,level,score) VALUES ('abc4@gmx.de', 'abc4', FALSE, 6, 450);
INSERT INTO main.users (email,name,anon,level,score) VALUES ('abc5@gmx.de', 'abc2', FALSE, 1, 650);


--create test connector
DO $$ 
begin
	PERFORM main.Create_Collector('Test',0);
	PERFORM main.Create_Collector('Test1',1);
END $$;
