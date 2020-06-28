CREATE TABLE user (
    user_id int NOT NULL AUTO_INCREMENT,
    user_name varchar(250) NOT NULL,
    dob DATE NOT NULL,
    email varchar(250) NOT NULL,
    phone_number varchar(10) NOT NULL,
    PRIMARY KEY (user_id)
); 