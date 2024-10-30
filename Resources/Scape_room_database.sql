DROP DATABASE IF EXISTS inventory;
CREATE DATABASE inventory;
USE inventory;

CREATE TABLE clue(
id CHAR(36) NOT NULL PRIMARY KEY,
category ENUM('Sensory', 'Alphabetical', 'Numerical','Combined') NOT NULL
);

CREATE TABLE deco(
id CHAR(36) NOT NULL PRIMARY KEY,
material ENUM('Wood', 'Glass', 'Plastic', 'Metal') NOT NULL
);

CREATE TABLE customer(
id CHAR(36) NOT NULL PRIMARY KEY,
customer_name VARCHAR(50) NOT NULL,
customer_surname VARCHAR(50) NOT NULL,
email VARCHAR(50) NOT NULL,
phone VARCHAR (15)NOT NULL
);

CREATE TABLE invoice(
id CHAR(36) NOT NULL PRIMARY KEY,
customer_id CHAR(36) NOT NULL,
total_price DOUBLE NOT NULL,
FOREIGN KEY (customer_id) REFERENCES customer(id)
);

CREATE TABLE escape(
id CHAR(36) NOT NULL PRIMARY KEY,
escape_name VARCHAR(50) NOT NULL
);

CREATE TABLE room(
id CHAR(36) NOT NULL PRIMARY KEY,
room_name VARCHAR(50) NOT NULL,
complete_time VARCHAR(20) NOT NULL,
lvl ENUM ('Beginner', 'Intermediate', 'Expert') NOT NULL,
theme ENUM ('Adventure', 'Mystery', 'Humour', 'History', 'Science') NOT NULL,
price DOUBLE NOT NULL,
enabled BOOLEAN NOT NULL,
escape_id CHAR(36) NOT NULL,
FOREIGN KEY (escape_id) REFERENCES escape(id),
UNIQUE(room_name)
);

CREATE TABLE item(
id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
clue_id CHAR(36),
deco_id CHAR(36),
name_item VARCHAR(50) NOT NULL,
enabled BOOLEAN NOT NULL,
available BOOLEAN NOT NULL,
price DOUBLE NOT NULL,
room_id CHAR(36),
FOREIGN KEY (clue_id) REFERENCES clue(id),
FOREIGN KEY (deco_id) REFERENCES deco(id),
FOREIGN KEY (room_id) REFERENCES room(id),
UNIQUE(clue_id, deco_id)
);

CREATE TABLE customer_made_room(
customer_id CHAR(36) NOT NULL,
room_id CHAR(36) NOT NULL,
FOREIGN KEY (customer_id) REFERENCES customer(id),
FOREIGN KEY (room_id) REFERENCES room(id)
);

INSERT INTO escape (id, escape_name)
VALUES
(1, "Escaping Java");