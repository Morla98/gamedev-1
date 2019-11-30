--
-- Populate the main schema with some dummy data
--

INSERT INTO main."users"(
    "email",
    "uid",
    "username",
    "anonymous",
    "level",
    "score",
    "fullname"
) VALUES (
    'user@example.com',
    '0000001',
    'example.user',
    TRUE,
    1,
    0,
    'Example User'
);
INSERT INTO main."users"(
    "email",
    "uid",
    "username",
    "anonymous",
    "level",
    "score",
    "fullname"
) VALUES (
    'user2@example.com',
    '0000002',
    'example2.user',
    TRUE,
    1,
    0,
    'Example User'
);

INSERT INTO main."collectors" (
    "id",
    "name",
    "last_seen"
) VALUES (
    'test-collector',
    'This is just a test collector',
    NOW()
);

INSERT INTO main."collectors" (
    "id",
    "name",
    "last_seen"
) VALUES (
    'test-collector2',
    'This is just a test collector2',
    NOW()
);


INSERT INTO main."achievements" (
    "id",
    "collector_id",
    "name",
    "description",
    "value"
) VALUES (
    'achievement-01',
    'test-collector',
    'Achievement 01',
    'This is the first achievement of the test collector!',
    10
);

INSERT INTO main."achievements" (
    "id",
    "collector_id",
    "name",
    "description",
    "value"
) VALUES (
    'achievement-02',
    'test-collector',
    'Achievement 02',
    'This is the second achievement of the test collector!',
    20
);

INSERT INTO main."achievements" (
    "id",
    "collector_id",
    "name",
    "description",
    "value"
) VALUES (
    'achievement-03',
    'test-collector2',
    'Achievement 03',
    'This is the first achievement of the second test collector!',
    20
);

INSERT INTO main."user_achievements"(
    "achievement_id",
    "collector_id",
    "user_email",
    "progress",
    "last_updated"
) VALUES (
    'achievement-01',
    'test-collector',
    'user@example.com',
    0,
    NOW()
);

INSERT INTO main."user_achievements"(
    "achievement_id",
    "collector_id",
    "user_email",
    "progress",
    "last_updated"
) VALUES (
    'achievement-01',
    'test-collector',
    'user2@example.com',
    0,
    NOW()
);

INSERT INTO main."user_achievements"(
    "achievement_id",
    "collector_id",
    "user_email",
    "progress",
    "last_updated"
) VALUES (
    'achievement-03',
    'test-collector2',
    'user@example.com',
    0,
    NOW()
);