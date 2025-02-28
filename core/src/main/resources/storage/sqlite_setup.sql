CREATE TABLE IF NOT EXISTS user_data
(
    uuid BINARY(128) NOT NULL PRIMARY KEY,
    username CHAR(17),
    data JSON
);