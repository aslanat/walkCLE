BEGIN TRANSACTION;

DROP TABLE IF EXISTS user_location, user_quest, location, quests, users; --suggested_locations --userQuestion, questions;

CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    profile_picture VARCHAR(200),
    username VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(200) NOT NULL,
    role VARCHAR(50) NOT NULL
);

CREATE TABLE quests(
    quest_name VARCHAR(50),
    badge_id INTEGER,
    quest_description VARCHAR(400),
    quest_category INTEGER UNIQUE PRIMARY KEY
);

CREATE TABLE location (
    location_pic VARCHAR(300),
    location_id SERIAL PRIMARY KEY,
    location_link VARCHAR(300),
    location_name VARCHAR(100) NOT NULL,
    address VARCHAR(200),
    description VARCHAR(400),
    wheelchair_accessible BOOLEAN,
    kid_friendly BOOLEAN,
    public_restroom BOOLEAN,
    cost VARCHAR(100),
    quest_category INTEGER,
    latitude DECIMAL(9, 6),
    longitude DECIMAL(9, 6),
    CONSTRAINT FK_location_quest FOREIGN KEY (quest_category) REFERENCES quests(quest_category)
);
--ALTER TABLE location //Daniel R. Added
--ADD COLUMN is_hidden_gem BOOLEAN DEFAULT FALSE;
--
--SELECT * FROM location WHERE is_hidden_gem = TRUE;

CREATE TABLE user_quest (
    user_id INTEGER,
    quest_category INTEGER,
    completed BOOLEAN,
    PRIMARY KEY (user_id, quest_category),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (quest_category) REFERENCES quests(quest_category)
);

CREATE TABLE user_location (
    user_id INTEGER,
    location_id INTEGER,
    timestamp TIMESTAMP,
    achieved BOOLEAN,  -- Add this line to store whether the location has been achieved/visited
    PRIMARY KEY (user_id, location_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (location_id) REFERENCES location(location_id)
);


--CREATE TABLE suggested_location (
--    suggested_location_id SERIAL PRIMARY KEY,
--    user_id INTEGER NOT NULL, -- The user who suggested the location
--    location_name VARCHAR(100) NOT NULL,
--    address VARCHAR(200),
--    location_link VARCHAR(300), -- Link to a website
--    description VARCHAR(400),
--    wheelchair_accessible BOOLEAN,
--    kid_friendly BOOLEAN,
--    public_restroom BOOLEAN,
--    cost VARCHAR(100),
--    quest_category INTEGER,
--    suggested_at TIMESTAMP DEFAULT NOW(),
--    FOREIGN KEY (user_id) REFERENCES users(user_id)
--);

--CREATE TABLE questions (
--    question_id SERIAL PRIMARY KEY,
--    question_text VARCHAR(500),
--    correct_answer VARCHAR(50),
--    quest_category INTEGER,
--    FOREIGN KEY (quest_category) REFERENCES quests(quest_category)
--);
--
--CREATE TABLE userQuestion (
--    user_id INTEGER,
--    question_id INTEGER,
--    quest_category INTEGER,
--    answer VARCHAR(500),
--    is_correct BOOLEAN,
--    PRIMARY KEY (user_id, question_id, quest_category),
--    FOREIGN KEY (user_id) REFERENCES users(user_id),
--    FOREIGN KEY (question_id) REFERENCES questions(question_id),
--    FOREIGN KEY (quest_category) REFERENCES quests(quest_category)
--);


-- ALTER TABLE user_location
-- ADD FOREIGN KEY (user_id) REFERENCES users(user_id);

-- ALTER TABLE user_quest
-- ADD FOREIGN KEY (user_id) REFERENCES users(user_id);

-- ALTER TABLE quests
-- ADD FOREIGN KEY (badge_id) REFERENCES user_quest(badge_id);

-- ALTER TABLE userQuestion
-- ADD FOREIGN KEY (user_id) REFERENCES users(user_id);

-- ALTER TABLE questions
-- ADD FOREIGN KEY (quest_id) REFERENCES quests(quest_id);

COMMIT TRANSACTION;


--ROLLBACK;

-- ROLLBACK;


-- SELECT * FROM location;
