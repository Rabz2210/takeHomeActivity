DROP TABLE if EXISTS PARKTABLE;
DROP TABLE if EXISTS USERS;

CREATE TABLE PARKTABLE(
                             ParkingSlot Int Not Null,
                             VehicleNumber VARCHAR(255) Not NULL,
                             PRIMARY KEY (ParkingSlot));


CREATE TABLE USERS(
VehicleNumber VARCHAR(255) Not NULL,
AGE INT NOT NULL,
PRIMARY KEY (VehicleNumber));