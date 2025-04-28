CREATE TABLE USERS (
                      id BIGINT PRIMARY KEY AUTO_INCREMENT,
                      name VARCHAR(100),
                      street VARCHAR(100),
                      house_Number VARCHAR(10),
                      pin_Code VARCHAR(10)
);

INSERT INTO Users (id, name, street, house_Number, pin_Code)
VALUES (106, 'Ishwari Doshi', 'isabela', '2591', '1112');

INSERT INTO Users (id, name, street, house_Number, pin_Code)
VALUES (107, 'John Doe', 'Main St', '123', '45678');

INSERT INTO Users (id, name, street, house_Number, pin_Code)
VALUES (108, 'Jane Smith', 'Elm St', '456', '98765');
