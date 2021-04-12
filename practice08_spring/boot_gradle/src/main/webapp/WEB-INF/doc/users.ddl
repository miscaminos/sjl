/**********************************/
/* Table Name: Members */
/**********************************/
CREATE TABLE users(
		usersno 		INT 				NOT NULL PRIMARY KEY,
		id 				VARCHAR(20) 		NOT NULL,
		passwd 			VARCHAR(60) 		NOT NULL,
		name 			VARCHAR(20) 		NOT NULL,
		phone 			VARCHAR(14) 		NOT NULL,
		zipcode 		VARCHAR(5),
		address1 		VARCHAR(100),
		address2 		VARCHAR(50),
		registerdate 	DATE 				NOT NULL
);