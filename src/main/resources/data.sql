DROP TABLE if EXISTS parkingslots;
DROP TABLE if EXISTS USERS;

CREATE TABLE parkingslots(
ParkingSlot Int Not Null,
VehicleNumber VARCHAR(255) Not NULL,
Occupied bool Not Null,
PRIMARY KEY (VehicleNumber));

CREATE TABLE USERS(
VehicleNumber VARCHAR(255) Not NULL,
AGE INT NOT NULL,
PRIMARY KEY (VehicleNumber));

