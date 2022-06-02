DROP TABLE IF EXISTS tgbot_management.tasks;
CREATE TABLE IF NOT EXISTS tgbot_management.tasks (
    id				bigserial NOT NULL PRIMARY KEY,
    task_name 		varchar(100) NOT NULL UNIQUE,
    task_note		varchar(250) NOT NULL
);

DROP TABLE IF EXISTS tgbot_management.trackings;
CREATE TABLE IF NOT EXISTS tgbot_management.trackings (
    id				bigserial NOT NULL PRIMARY KEY,
    start_time		TIMESTAMP WITHOUT TIME ZONE,
    end_time 		TIMESTAMP WITHOUT TIME ZONE,
    tracking_note	varchar(100) NOT NULL,
    task			bigint,
    "user" 			bigint,
    FOREIGN KEY (task) REFERENCES tgbot_management.tasks(id)
);

DROP TABLE IF EXISTS tgbot_management.reports;
CREATE TABLE IF NOT EXISTS tgbot_management.reports (
    id				bigserial NOT NULL PRIMARY KEY,
    "date" 			date NOT NULL,
    full_time		bigint,
    "user" 			bigint
);
