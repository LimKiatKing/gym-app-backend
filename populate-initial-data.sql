/* Delete the tables if they already exist */
DROP TABLE IF EXISTS friend_list;
DROP TABLE IF EXISTS Booking;
DROP TABLE IF EXISTS Timeslot;
DROP TABLE IF EXISTS Gym_Pass;
DROP TABLE IF EXISTS Gym;
DROP TABLE IF EXISTS User;


/* Create the schema */
CREATE TABLE User (
	user_id INT(8) AUTO_INCREMENT,
	name VARCHAR(50),
	gender CHAR(1), 
	credit INT(8),
	PRIMARY KEY(user_id) 
	) engine=InnoDB;

CREATE TABLE Gym (
	gym_id VARCHAR(8),
	gym_name VARCHAR(50),
	address VARCHAR(500),
	pass_price INT(8), 
	PRIMARY KEY(gym_id) 
	) engine=InnoDB;

CREATE TABLE Gym_Pass (
	pass_id INT AUTO_INCREMENT,
	gym_id VARCHAR(8),
	user_id INT(8),
	expiry_date DATE,
	balance INT(8),
	PRIMARY KEY(pass_id),
	FOREIGN KEY (gym_id) REFERENCES Gym(gym_id) ON DELETE CASCADE,	 
	FOREIGN KEY (user_id) REFERENCES User(user_id) ON DELETE CASCADE
	) engine=InnoDB;
	
CREATE TABLE Timeslot (
	slot_id INT AUTO_INCREMENT,
	slot_date DATE,
	slot_number INT(8),
	gym_id VARCHAR(8),
	description VARCHAR(200),
	size INT(8), 
	PRIMARY KEY(slot_id),
	FOREIGN KEY (gym_id) REFERENCES Gym(gym_id) ON DELETE CASCADE
	) engine=InnoDB;
		
CREATE TABLE Booking (
	booking_id INT AUTO_INCREMENT,
	user_id INT(8),
	slot_id INT,
	pass_id INT,
	PRIMARY KEY(booking_id),
	FOREIGN KEY (user_id) REFERENCES User(user_id) ON DELETE CASCADE,
	FOREIGN KEY (slot_id) REFERENCES Timeslot(slot_id) ON DELETE CASCADE,
	FOREIGN KEY (pass_id) REFERENCES Gym_Pass(pass_id) ON DELETE CASCADE
	) engine=InnoDB;
		
CREATE TABLE Friend_List (
	person_id INT(8),
	friend_id INT(8),
	status_code CHAR(2),
	PRIMARY KEY(person_id, friend_id),
	FOREIGN KEY (person_id) REFERENCES User(user_id) ON DELETE CASCADE,
	FOREIGN KEY (friend_id) REFERENCES User(user_id) ON DELETE CASCADE,
	CHECK (person_id < friend_id)
	) engine=INNODB;
	

/* Populate the tables with initial data */

/* user table ----------------------------------------------------------------*/
INSERT INTO User VALUES ('1001', 'User1', 'M', 0);
INSERT INTO User VALUES ('1002', 'User2', 'F', 100);
INSERT INTO User VALUES ('1003', 'User3', 'M', 50);
INSERT INTO User VALUES ('1004', 'User4', 'F', 300);
INSERT INTO User VALUES ('1005', 'User5', 'F', 200);
INSERT INTO User VALUES ('1006', 'User6', 'M', 0);
INSERT INTO User VALUES ('1007', 'User7', 'F', 150);
INSERT INTO User VALUES ('1008', 'User8', 'M', 50);
INSERT INTO User VALUES ('1009', 'User9', 'F', 300);

/* gym table ----------------------------------------------------------------*/
INSERT INTO Gym VALUES ('G001', 'SG Gym', '22 Sing Drive, Pore Building, #22-222', 200);
INSERT INTO Gym VALUES ('G002', 'Fit Bloc', '87 Science Park Dr, #03-02 The Oasis, Singapore 118260', 180);
INSERT INTO Gym VALUES ('G003', 'Gym Fit', '111 Fit Street, Singapore 111111', 150);

/* Gym_Pass table ----------------------------------------------------------------*/
INSERT INTO Gym_Pass(gym_id, user_id, expiry_date, balance) VALUES ('G003', '1001', '2022-03-09', 2);
INSERT INTO Gym_Pass(gym_id, user_id, expiry_date, balance) VALUES ('G003', '1001', '2023-07-30', 5);
INSERT INTO Gym_Pass(gym_id, user_id, expiry_date, balance) VALUES ('G002', '1002', '2023-07-19', 2);
INSERT INTO Gym_Pass(gym_id, user_id, expiry_date, balance) VALUES ('G001', '1004', '2023-08-02', 6);
INSERT INTO Gym_Pass(gym_id, user_id, expiry_date, balance) VALUES ('G001', '1005', '2023-08-20', 8);



/* timeslot table ----------------------------------------------------------------*/
INSERT INTO Timeslot(slot_date, slot_number, gym_id, description, size) VALUES ('2023-06-12', '1', 'G001', 'Time: 10:00am - 01:00pm', 5);
INSERT INTO Timeslot(slot_date, slot_number, gym_id, description, size) VALUES ('2023-06-12', '2', 'G001', 'Time: 02:00pm - 05:00pm', 5);
INSERT INTO Timeslot(slot_date, slot_number, gym_id, description, size) VALUES ('2023-06-12', '3', 'G001', 'Time: 06:00pm - 09:00pm', 5);
INSERT INTO Timeslot(slot_date, slot_number, gym_id, description, size) VALUES ('2023-06-12', '1', 'G002', 'Time: 02:00pm - 05:00pm', 6);
INSERT INTO Timeslot(slot_date, slot_number, gym_id, description, size) VALUES ('2023-06-12', '2', 'G002', 'Time: 06:00pm - 09:00pm', 6);
INSERT INTO Timeslot(slot_date, slot_number, gym_id, description, size) VALUES ('2023-06-12', '1', 'G003', 'Time: 02:00pm - 09:00pm', 10);

INSERT INTO Timeslot(slot_date, slot_number, gym_id, description, size) VALUES ('2023-06-13', '1', 'G001', 'Time: 10:00am - 01:00pm', 5);
INSERT INTO Timeslot(slot_date, slot_number, gym_id, description, size) VALUES ('2023-06-13', '2', 'G001', 'Time: 02:00pm - 05:00pm', 5);
INSERT INTO Timeslot(slot_date, slot_number, gym_id, description, size) VALUES ('2023-06-13', '3', 'G001', 'Time: 06:00pm - 09:00pm', 5);
INSERT INTO Timeslot(slot_date, slot_number, gym_id, description, size) VALUES ('2023-06-13', '1', 'G002', 'Time: 02:00pm - 05:00pm', 6);
INSERT INTO Timeslot(slot_date, slot_number, gym_id, description, size) VALUES ('2023-06-13', '2', 'G002', 'Time: 06:00pm - 09:00pm', 6);
INSERT INTO Timeslot(slot_date, slot_number, gym_id, description, size) VALUES ('2023-06-13', '1', 'G003', 'Time: 02:00pm - 09:00pm', 10);

INSERT INTO Timeslot(slot_date, slot_number, gym_id, description, size) VALUES ('2023-06-14', '1', 'G001', 'Time: 10:00am - 01:00pm', 5);
INSERT INTO Timeslot(slot_date, slot_number, gym_id, description, size) VALUES ('2023-06-14', '2', 'G001', 'Time: 02:00pm - 05:00pm', 5);
INSERT INTO Timeslot(slot_date, slot_number, gym_id, description, size) VALUES ('2023-06-14', '3', 'G001', 'Time: 06:00pm - 09:00pm', 5);
INSERT INTO Timeslot(slot_date, slot_number, gym_id, description, size) VALUES ('2023-06-14', '1', 'G002', 'Time: 02:00pm - 05:00pm', 6);
INSERT INTO Timeslot(slot_date, slot_number, gym_id, description, size) VALUES ('2023-06-14', '2', 'G002', 'Time: 06:00pm - 09:00pm', 6);
INSERT INTO Timeslot(slot_date, slot_number, gym_id, description, size) VALUES ('2023-06-14', '1', 'G003', 'Time: 02:00pm - 09:00pm', 10);

INSERT INTO Timeslot(slot_date, slot_number, gym_id, description, size) VALUES ('2023-06-15', '1', 'G001', 'Time: 10:00am - 01:00pm', 5);
INSERT INTO Timeslot(slot_date, slot_number, gym_id, description, size) VALUES ('2023-06-15', '2', 'G001', 'Time: 02:00pm - 05:00pm', 5);
INSERT INTO Timeslot(slot_date, slot_number, gym_id, description, size) VALUES ('2023-06-15', '3', 'G001', 'Time: 06:00pm - 09:00pm', 5);
INSERT INTO Timeslot(slot_date, slot_number, gym_id, description, size) VALUES ('2023-06-15', '1', 'G002', 'Time: 02:00pm - 05:00pm', 6);
INSERT INTO Timeslot(slot_date, slot_number, gym_id, description, size) VALUES ('2023-06-15', '2', 'G002', 'Time: 06:00pm - 09:00pm', 6);
INSERT INTO Timeslot(slot_date, slot_number, gym_id, description, size) VALUES ('2023-06-15', '1', 'G003', 'Time: 02:00pm - 09:00pm', 10);

INSERT INTO Timeslot(slot_date, slot_number, gym_id, description, size) VALUES ('2023-06-16', '1', 'G001', 'Time: 10:00am - 01:00pm', 5);
INSERT INTO Timeslot(slot_date, slot_number, gym_id, description, size) VALUES ('2023-06-16', '2', 'G001', 'Time: 02:00pm - 05:00pm', 5);
INSERT INTO Timeslot(slot_date, slot_number, gym_id, description, size) VALUES ('2023-06-16', '3', 'G001', 'Time: 06:00pm - 09:00pm', 5);
INSERT INTO Timeslot(slot_date, slot_number, gym_id, description, size) VALUES ('2023-06-16', '1', 'G002', 'Time: 02:00pm - 05:00pm', 6);
INSERT INTO Timeslot(slot_date, slot_number, gym_id, description, size) VALUES ('2023-06-16', '2', 'G002', 'Time: 06:00pm - 09:00pm', 6);
INSERT INTO Timeslot(slot_date, slot_number, gym_id, description, size) VALUES ('2023-06-16', '1', 'G003', 'Time: 02:00pm - 09:00pm', 10);

INSERT INTO Timeslot(slot_date, slot_number, gym_id, description, size) VALUES ('2023-06-17', '1', 'G001', 'Time: 10:00am - 01:00pm', 5);
INSERT INTO Timeslot(slot_date, slot_number, gym_id, description, size) VALUES ('2023-06-17', '2', 'G001', 'Time: 02:00pm - 05:00pm', 5);
INSERT INTO Timeslot(slot_date, slot_number, gym_id, description, size) VALUES ('2023-06-17', '3', 'G001', 'Time: 06:00pm - 09:00pm', 5);
INSERT INTO Timeslot(slot_date, slot_number, gym_id, description, size) VALUES ('2023-06-17', '1', 'G002', 'Time: 02:00pm - 05:00pm', 6);
INSERT INTO Timeslot(slot_date, slot_number, gym_id, description, size) VALUES ('2023-06-17', '2', 'G002', 'Time: 06:00pm - 09:00pm', 6);
INSERT INTO Timeslot(slot_date, slot_number, gym_id, description, size) VALUES ('2023-06-17', '1', 'G003', 'Time: 02:00pm - 09:00pm', 10);

INSERT INTO Timeslot(slot_date, slot_number, gym_id, description, size) VALUES ('2023-06-18', '1', 'G001', 'Time: 10:00am - 01:00pm', 5);
INSERT INTO Timeslot(slot_date, slot_number, gym_id, description, size) VALUES ('2023-06-18', '2', 'G001', 'Time: 02:00pm - 05:00pm', 5);
INSERT INTO Timeslot(slot_date, slot_number, gym_id, description, size) VALUES ('2023-06-18', '1', 'G002', 'Time: 02:00pm - 05:00pm', 6);
INSERT INTO Timeslot(slot_date, slot_number, gym_id, description, size) VALUES ('2023-06-18', '1', 'G003', 'Time: 02:00pm - 09:00pm', 10);

INSERT INTO Timeslot(slot_date, slot_number, gym_id, description, size) VALUES ('2023-06-19', '1', 'G001', 'Time: 10:00am - 01:00pm', 5);
INSERT INTO Timeslot(slot_date, slot_number, gym_id, description, size) VALUES ('2023-06-19', '2', 'G001', 'Time: 02:00pm - 05:00pm', 5);
INSERT INTO Timeslot(slot_date, slot_number, gym_id, description, size) VALUES ('2023-06-19', '1', 'G002', 'Time: 02:00pm - 05:00pm', 6);
INSERT INTO Timeslot(slot_date, slot_number, gym_id, description, size) VALUES ('2023-06-19', '1', 'G003', 'Time: 02:00pm - 09:00pm', 10);

INSERT INTO Timeslot(slot_date, slot_number, gym_id, description, size) VALUES ('2023-06-20', '1', 'G001', 'Time: 10:00am - 01:00pm', 5);
INSERT INTO Timeslot(slot_date, slot_number, gym_id, description, size) VALUES ('2023-06-20', '2', 'G001', 'Time: 02:00pm - 05:00pm', 5);
INSERT INTO Timeslot(slot_date, slot_number, gym_id, description, size) VALUES ('2023-06-20', '1', 'G002', 'Time: 02:00pm - 05:00pm', 6);
INSERT INTO Timeslot(slot_date, slot_number, gym_id, description, size) VALUES ('2023-06-20', '1', 'G003', 'Time: 02:00pm - 09:00pm', 10);

/* booking table ----------------------------------------------------------------*/
INSERT INTO Booking(user_id, slot_id, pass_id) VALUES ('1001', '13', '2');
INSERT INTO Booking(user_id, slot_id, pass_id) VALUES ('1001', '12', '2');
INSERT INTO Booking(user_id, slot_id, pass_id) VALUES ('1004', '3', '4');
INSERT INTO Booking(user_id, slot_id, pass_id) VALUES ('1005', '3', '5');
/*
INSERT INTO Booking(user_id, slot_id, pass_id) VALUES ('1001', '2', '1');
INSERT INTO Booking(user_id, slot_id, pass_id) VALUES ('1001', '1', '1');
*/

/* Friend_List table ----------------------------------------------------------------*/
INSERT INTO Friend_List VALUES ('1001', '1002', 'A');
INSERT INTO Friend_List VALUES ('1001', '1003', 'P2');
INSERT INTO Friend_List VALUES ('1001', '1005', 'P1');
INSERT INTO Friend_List VALUES ('1002', '1006', 'P2');
INSERT INTO Friend_List VALUES ('1001', '1007', 'P1');
INSERT INTO Friend_List VALUES ('1004', '1005', 'A');
INSERT INTO Friend_List VALUES ('1006', '1008', 'P1');



