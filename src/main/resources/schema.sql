CREATE TABLE todo
(
    id INTEGER NOT NULL AUTO_INCREMENT,
    user VARCHAR(128),
    description VARCHAR(225),
    completed BIT(1)
    PRIMARY KEY (id)
)