USE my_db;

CREATE TABLE users (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       username VARCHAR(50) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       `first_name` VARCHAR(255) NOT NULL,
                       `last_name` VARCHAR(255) NOT NULL,
                       `email` VARCHAR(255) NOT NULL,
                       `phone_number` VARCHAR(255) NOT NULL,
                       `date` VARCHAR(255) NOT NULL,
                       `gender` VARCHAR(45) NOT NULL,
                       UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
                       UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
                       UNIQUE INDEX `phone_number_UNIQUE` (`phone_number` ASC) VISIBLE ,
                       enabled BOOLEAN NOT NULL
);


CREATE TABLE authorities (
                             id BIGINT PRIMARY KEY AUTO_INCREMENT,
                             name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE user_authority (
                                user_id BIGINT NOT NULL,
                                authority_id BIGINT NOT NULL,
                                PRIMARY KEY (user_id, authority_id),
                                FOREIGN KEY (user_id) REFERENCES users(id),
                                FOREIGN KEY (authority_id) REFERENCES authorities(id)
);

CREATE TABLE products (
                          product_id BIGINT PRIMARY KEY AUTO_INCREMENT,
                          product_name VARCHAR(100),
                          price INT,
                          description TEXT,
                          image BLOB
);
CREATE TABLE orders (
                        order_id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        user_id BIGINT,
                        order_date VARCHAR(255) NOT NULL,
                        product_id BIGINT,
                        address VARCHAR(250) NOT NULL,
                        city VARCHAR(250) NOT NULL,
                        country VARCHAR(250) NOT NULL,
                        FOREIGN KEY (product_id) REFERENCES products(product_id),
                        FOREIGN KEY (user_id) REFERENCES users(id)
);


CREATE TABLE liked_products (
                                like_id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                user_id BIGINT,
                                product_id BIGINT,
                                FOREIGN KEY (user_id) REFERENCES users(id),
                                FOREIGN KEY (product_id) REFERENCES products(product_id)
);
CREATE TABLE cards (
                       card_id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       user_id BIGINT,
                       card_number VARCHAR(16),
                       card_holder VARCHAR(100),
                       expiration_date VARCHAR(255),
                       FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE basket (
                        basket_id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        user_id BIGINT,
                        product_id BIGINT,
                        quantity INT,
                        FOREIGN KEY (user_id) REFERENCES users(id),
                        FOREIGN KEY (product_id) REFERENCES products(product_id)
);
INSERT INTO `my_db`.`authorities` (`name`) VALUES ('USER');
INSERT INTO `my_db`.`authorities` (`name`) VALUES ('ADMIN');