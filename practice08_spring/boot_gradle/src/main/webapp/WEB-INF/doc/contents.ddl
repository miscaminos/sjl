/**********************************/
/* Table Name: contents per topic */
/**********************************/
CREATE TABLE contents(
		contentsno 			INT 						NOT NULL PRIMARY KEY,
		topicno 			INT,
		title 				VARCHAR(50) 				NOT NULL,
		content 			VARCHAR(800) 				NOT NULL,
		userno 				INT,
		cnt 				INT 		DEFAULT 0 		NOT NULL,
		passwd 				VARCHAR(60) 				NOT NULL,
		rdate 				DATE 						NOT NULL,
		imgfile1 			VARCHAR(100),
		thumb1 				VARCHAR(100),
		size1 				INT			DEFAULT 0,
		usersno 			INT,
  FOREIGN KEY (topicno) REFERENCES topic (topicno),
  FOREIGN KEY (usersno) REFERENCES users (usersno)
);

