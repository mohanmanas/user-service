CREATE TABLE user (
    user_id int NOT NULL AUTO_INCREMENT,
    user_name varchar(250) NOT NULL,
    dob DATE NOT NULL,
    email varchar(250) NOT NULL,
    phone_number varchar(10) NOT NULL,
    created_user varchar(250),
    created_date_time DATE,
    modified_user varchar(250),
    modified_date_time DATE,
    version BIGINT,
    PRIMARY KEY (user_id)
); 