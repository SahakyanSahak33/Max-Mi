-- Create the database
CREATE DATABASE IF NOT EXISTS my_db;
USE my_db;

SET FOREIGN_KEY_CHECKS = 0;

-- Create the 'products' table
CREATE TABLE products (
                          product_id   BIGINT PRIMARY KEY AUTO_INCREMENT,
                          product_name VARCHAR(100),
                          price        INT,
                          rating       INT,
                          discount     INT,
                          description  TEXT,
                          image        BLOB
);

-- Create the 'authorities' table
CREATE TABLE authorities (
                             id   BIGINT PRIMARY KEY AUTO_INCREMENT,
                             name VARCHAR(50) NOT NULL UNIQUE
);

-- Create the 'basket' table
CREATE TABLE basket (
                        basket_id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        user_id   BIGINT,
                        FOREIGN KEY (user_id) REFERENCES users (id)
);

-- Create the 'users' table
CREATE TABLE users (
                       id             BIGINT PRIMARY KEY AUTO_INCREMENT,
                       basket_id      BIGINT NOT NULL,
                       username       VARCHAR(50)  NOT NULL,
                       password       VARCHAR(255) NOT NULL,
                       first_name     VARCHAR(255) NOT NULL,
                       last_name      VARCHAR(255) NOT NULL,
                       email          VARCHAR(255) NOT NULL,
                       phone_number   VARCHAR(255) NOT NULL,
                       date           VARCHAR(255) NOT NULL,
                       gender         VARCHAR(45)  NOT NULL,
                       enabled        BOOLEAN      NOT NULL,
                       UNIQUE INDEX username_UNIQUE (username ASC),
                       UNIQUE INDEX email_UNIQUE (email ASC),
                       UNIQUE INDEX phone_number_UNIQUE (phone_number ASC),
                       FOREIGN KEY (basket_id) REFERENCES basket(basket_id)
);

-- Create the 'cards' table
CREATE TABLE cards (
                       card_id         BIGINT AUTO_INCREMENT PRIMARY KEY,
                       user_id         BIGINT       NULL,
                       card_number     VARCHAR(19)  NULL,
                       card_holder     VARCHAR(3) NULL,
                       expiration_date VARCHAR(5) NULL,
                       FOREIGN KEY (user_id) REFERENCES users (id)
);

-- Create the 'user_authority' table
CREATE TABLE user_authority (
                                user_id      BIGINT NOT NULL,
                                authority_id BIGINT NOT NULL,
                                PRIMARY KEY (user_id, authority_id),
                                FOREIGN KEY (user_id) REFERENCES users (id),
                                FOREIGN KEY (authority_id) REFERENCES authorities (id)
);

-- Create the 'orders' table
CREATE TABLE orders (
                        order_id   BIGINT PRIMARY KEY AUTO_INCREMENT,
                        user_id    BIGINT,
                        order_date VARCHAR(255) NOT NULL,
                        product_id BIGINT,
                        address    VARCHAR(250) NOT NULL,
                        city       VARCHAR(250) NOT NULL,
                        country    VARCHAR(250) NOT NULL,
                        FOREIGN KEY (user_id) REFERENCES users(id),
                        FOREIGN KEY (product_id) REFERENCES products(product_id)
);
CREATE TABLE basket_products
(
    basket_id  BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    PRIMARY KEY (basket_id, product_id),
    FOREIGN KEY (basket_id) REFERENCES basket(basket_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);
CREATE TABLE liked_products
(
    user_id    BIGINT,
    product_id BIGINT,
    PRIMARY KEY (user_id, product_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (product_id) REFERENCES products (product_id)
);

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO `my_db`.`authorities` (`name`)
VALUES ('USER');
INSERT INTO `my_db`.`authorities` (`name`)
VALUES ('ADMIN');

INSERT INTO products (price, rating , product_name, discount, description, image)
VALUES (1200, 5 , 'Iphone14 Pro', 40, 'Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro',
        LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/my_folder/Iphone22.png'));
INSERT INTO products (price, rating , product_name, discount, description, image)
VALUES (1200, 5 , 'Asus VivoBook 14 k413jq 16 GB ram / 512 GB SSD', 40, 'Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro',
        LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/my_folder/Note.png'));
INSERT INTO products (price, rating , product_name, discount, description, image)
VALUES (1200, 5 , 'Iphone14 Pro', 40, 'Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro',
        LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/my_folder/tvsmall.png'));
INSERT INTO products (price, rating , product_name, discount, description, image)
VALUES (1200, 5 , 'Asus VivoBook 14 k413jq 16 GB ram / 512 GB SSD', 40, 'Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro',
        LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/my_folder/smartwatch.png'));
INSERT INTO products (price, rating , product_name, discount, description, image)
VALUES (1200, 5 , 'Iphone14 Pro', 40, 'Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro',
        LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/my_folder/airpod.png'));
INSERT INTO products (price, rating , product_name, discount, description, image)
VALUES (1200, 5 , 'Asus VivoBook 14 k413jq 16 GB ram / 512 GB SSD', 40, 'Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro',
        LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/my_folder/braksmartwatch.png'));
INSERT INTO products (price, rating , product_name, discount, description, image)
VALUES (1200, 5 , 'Iphone14 Pro', 40, 'Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro',
        LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/my_folder/urish_note.png'));
INSERT INTO products (price, rating , product_name, discount, description, image)
VALUES (1200, 5 , 'Asus VivoBook 14 k413jq 16 GB ram / 512 GB SSD', 40, 'Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro',
        LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/my_folder/hoco.png'));
INSERT INTO products (price, rating , product_name, discount, description, image)
VALUES (1200, 5 , 'Iphone14 Pro', 40, 'Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro',
        LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/my_folder/urish_note2.png'));
INSERT INTO products (price, rating , product_name, discount, description, image)
VALUES (1200, 5 , 'Asus VivoBook 14 k413jq 16 GB ram / 512 GB SSD', 40, 'Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro',
        LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/my_folder/smarthfon.png'));
INSERT INTO products (price, rating , product_name, discount, description, image)
VALUES (1200, 5 , 'Iphone14 Pro', 40, 'Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro',
        LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/my_folder/smartwatch.png'));
INSERT INTO products (price, rating , product_name, discount, description, image)
VALUES (1200, 5 , 'Asus VivoBook 14 k413jq 16 GB ram / 512 GB SSD', 40, 'Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro',
        LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/my_folder/tv.png'));
INSERT INTO products (price, rating , product_name, discount, description, image)
VALUES (1200, 5 , 'Iphone14 Pro', 40, 'Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro',
        LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/my_folder/Iphone22.png'));
INSERT INTO products (price, rating , product_name, discount, description, image)
VALUES (1200, 5 , 'Asus VivoBook 14 k413jq 16 GB ram / 512 GB SSD', 40, 'Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro',
        LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/my_folder/Note.png'));
INSERT INTO products (price, rating , product_name, discount, description, image)
VALUES (1200, 5 , 'Iphone14 Pro', 40, 'Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro',
        LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/my_folder/tvsmall.png'));
INSERT INTO products (price, rating , product_name, discount, description, image)
VALUES (1200, 5 , 'Asus VivoBook 14 k413jq 16 GB ram / 512 GB SSD', 40, 'Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro',
        LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/my_folder/smartwatch.png'));
INSERT INTO products (price, rating , product_name, discount, description, image)
VALUES (1200, 5 , 'Iphone14 Pro', 40, 'Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro',
        LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/my_folder/airpod.png'));
INSERT INTO products (price, rating , product_name, discount, description, image)
VALUES (1200, 5 , 'Asus VivoBook 14 k413jq 16 GB ram / 512 GB SSD', 40, 'Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro',
        LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/my_folder/braksmartwatch.png'));
INSERT INTO products (price, rating , product_name, discount, description, image)
VALUES (1200, 5 , 'Iphone14 Pro', 40, 'Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro',
        LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/my_folder/urish_note.png'));
INSERT INTO products (price, rating , product_name, discount, description, image)
VALUES (1200, 5 , 'Asus VivoBook 14 k413jq 16 GB ram / 512 GB SSD', 40, 'Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro',
        LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/my_folder/hoco.png'));
INSERT INTO products (price, rating , product_name, discount, description, image)
VALUES (1200, 5 , 'Iphone14 Pro', 40, 'Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro',
        LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/my_folder/urish_note2.png'));
INSERT INTO products (price, rating , product_name, discount, description, image)
VALUES (1200, 5 , 'Asus VivoBook 14 k413jq 16 GB ram / 512 GB SSD', 40, 'Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro',
        LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/my_folder/smarthfon.png'));
INSERT INTO products (price, rating , product_name, discount, description, image)
VALUES (1200, 5 , 'Iphone14 Pro', 40, 'Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro',
        LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/my_folder/smartwatch.png'));
INSERT INTO products (price, rating , product_name, discount, description, image)
VALUES (1200, 5 , 'Asus VivoBook 14 k413jq 16 GB ram / 512 GB SSD', 40, 'Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro',
        LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/my_folder/tv.png'));
INSERT INTO products (price, rating , product_name, discount, description, image)
VALUES (1200, 5 , 'Iphone14 Pro', 40, 'Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro',
        LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/my_folder/Iphone22.png'));
INSERT INTO products (price, rating , product_name, discount, description, image)
VALUES (1200, 5 , 'Asus VivoBook 14 k413jq 16 GB ram / 512 GB SSD', 40, 'Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro',
        LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/my_folder/Note.png'));
INSERT INTO products (price, rating , product_name, discount, description, image)
VALUES (1200, 5 , 'Iphone14 Pro', 40, 'Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro',
        LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/my_folder/tvsmall.png'));
INSERT INTO products (price, rating , product_name, discount, description, image)
VALUES (1200, 5 , 'Asus VivoBook 14 k413jq 16 GB ram / 512 GB SSD', 40, 'Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro',
        LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/my_folder/smartwatch.png'));
INSERT INTO products (price, rating , product_name, discount, description, image)
VALUES (1200, 5 , 'Iphone14 Pro', 40, 'Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro',
        LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/my_folder/airpod.png'));
INSERT INTO products (price, rating , product_name, discount, description, image)
VALUES (1200, 5 , 'Asus VivoBook 14 k413jq 16 GB ram / 512 GB SSD', 40, 'Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro',
        LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/my_folder/braksmartwatch.png'));
INSERT INTO products (price, rating , product_name, discount, description, image)
VALUES (1200, 5 , 'Iphone14 Pro', 40, 'Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro',
        LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/my_folder/urish_note.png'));
INSERT INTO products (price, rating , product_name, discount, description, image)
VALUES (1200, 5 , 'Asus VivoBook 14 k413jq 16 GB ram / 512 GB SSD', 40, 'Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro',
        LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/my_folder/hoco.png'));
INSERT INTO products (price, rating , product_name, discount, description, image)
VALUES (1200, 5 , 'Iphone14 Pro', 40, 'Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro',
        LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/my_folder/urish_note2.png'));
INSERT INTO products (price, rating , product_name, discount, description, image)
VALUES (1200, 5 , 'Asus VivoBook 14 k413jq 16 GB ram / 512 GB SSD', 40, 'Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro',
        LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/my_folder/smarthfon.png'));
INSERT INTO products (price, rating , product_name, discount, description, image)
VALUES (1200, 5 , 'Iphone14 Pro', 40, 'Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro',
        LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/my_folder/smartwatch.png'));
INSERT INTO products (price, rating , product_name, discount, description, image)
VALUES (1200, 5 , 'Asus VivoBook 14 k413jq 16 GB ram / 512 GB SSD', 40, 'Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro',
        LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/my_folder/tv.png'));
INSERT INTO products (price, rating , product_name, discount, description, image)
VALUES (1200, 5 , 'Iphone14 Pro', 40, 'Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro',
        LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/my_folder/Iphone22.png'));
INSERT INTO products (price, rating , product_name, discount, description, image)
VALUES (1200, 5 , 'Asus VivoBook 14 k413jq 16 GB ram / 512 GB SSD', 40, 'Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro',
        LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/my_folder/Note.png'));
INSERT INTO products (price, rating , product_name, discount, description, image)
VALUES (1200, 5 , 'Iphone14 Pro', 40, 'Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro',
        LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/my_folder/tvsmall.png'));
INSERT INTO products (price, rating , product_name, discount, description, image)
VALUES (1200, 5 , 'Asus VivoBook 14 k413jq 16 GB ram / 512 GB SSD', 40, 'Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro',
        LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/my_folder/smartwatch.png'));
INSERT INTO products (price, rating , product_name, discount, description, image)
VALUES (1200, 5 , 'Iphone14 Pro', 40, 'Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro',
        LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/my_folder/airpod.png'));
INSERT INTO products (price, rating , product_name, discount, description, image)
VALUES (1200, 5 , 'Asus VivoBook 14 k413jq 16 GB ram / 512 GB SSD', 40, 'Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro',
        LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/my_folder/braksmartwatch.png'));
INSERT INTO products (price, rating , product_name, discount, description, image)
VALUES (1200, 5 , 'Iphone14 Pro', 40, 'Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro',
        LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/my_folder/urish_note.png'));
INSERT INTO products (price, rating , product_name, discount, description, image)
VALUES (1200, 5 , 'Asus VivoBook 14 k413jq 16 GB ram / 512 GB SSD', 40, 'Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro',
        LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/my_folder/hoco.png'));
INSERT INTO products (price, rating , product_name, discount, description, image)
VALUES (1200, 5 , 'Iphone14 Pro', 40, 'Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro',
        LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/my_folder/urish_note2.png'));
INSERT INTO products (price, rating , product_name, discount, description, image)
VALUES (1200, 5 , 'Asus VivoBook 14 k413jq 16 GB ram / 512 GB SSD', 40, 'Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro',
        LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/my_folder/smarthfon.png'));
INSERT INTO products (price, rating , product_name, discount, description, image)
VALUES (1200, 5 , 'Iphone14 Pro', 40, 'Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro',
        LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/my_folder/smartwatch.png'));
INSERT INTO products (price, rating , product_name, discount, description, image)
VALUES (1200, 5 , 'Asus VivoBook 14 k413jq 16 GB ram / 512 GB SSD', 40, 'Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro Description of Iphone 14 Pro of Iphone 14 Pro',
        LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/my_folder/tv.png'));