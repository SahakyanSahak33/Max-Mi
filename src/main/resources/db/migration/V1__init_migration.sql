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
/*
CREATE TABLE `my_db`.`user_details` (
                                        `id` BIGINT NOT NULL AUTO_INCREMENT,
                                        `username` VARCHAR(255) NOT NULL,
                                        `first_name` VARCHAR(255) NOT NULL,
                                        `last_name` VARCHAR(255) NOT NULL,
                                        `email` VARCHAR(255) NOT NULL,
                                        `phone_number` VARCHAR(255) NOT NULL,
                                        `date` VARCHAR(255) NOT NULL,
                                        `gender` VARCHAR(45) NOT NULL,
                                        PRIMARY KEY (`id`),
                                        UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
                                        UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
                                        UNIQUE INDEX `phone_number_UNIQUE` (`phone_number` ASC) VISIBLE ,
                                        FOREIGN KEY (username) references users(username));*/