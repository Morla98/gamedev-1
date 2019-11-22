--create Users
INSERT INTO main.users (email, userName, anonymous, level, score, fullName) VALUES ('abc@gmx.de', 'abc', FALSE, 3, 300, 'abc Mustermann');
INSERT INTO main.users (email, userName, anonymous, level, score, fullName) VALUES ('abc1@gmx.de', 'abc', TRUE, 1, 500, 'abc1 Mustermann');
INSERT INTO main.users (email, userName, anonymous, level, score, fullName) VALUES ('abc2@gmx.de', 'abc2', FALSE, -5, -120, 'abc2 Mustermann');
INSERT INTO main.Collector (Name) VALUES ( 'GIT');
INSERT INTO main.Collector (Name) VALUES ('JIRA');
INSERT INTO main.achievements (AID,CID,description,name,value) VALUES (0,1,'#commits > 10', 'hard-workin', 100);
INSERT INTO main.achievements (AID,CID,description,name,value) VALUES (1,1,'#commits > 100', 'harder-working', 500);
INSERT INTO main.UserAchievement (AID,CID,email,progress,last_updated) VALUES (0,1,'abc@gmx.de',50,NOW());
INSERT INTO main.UserAchievement (AID,CID,email,progress,last_updated) VALUES (1,1,'abc@gmx.de',50,NOW());
INSERT INTO main.UserAchievement (AID,CID,email,progress,last_updated) VALUES (0,1,'abc1@gmx.de',50,NOW());
INSERT INTO main.UserAchievement (AID,CID,email,progress,last_updated) VALUES (1,1,'abc1@gmx.de',50,NOW());
INSERT INTO main.UserAchievement (AID,CID,email,progress,last_updated) VALUES (0,1,'abc2@gmx.de',50,NOW());
INSERT INTO main.UserAchievement (AID,CID,email,progress,last_updated) VALUES (1,1,'abc2@gmx.de',50,NOW());