/**********************************/
/* Table Name: Topics within a chapter */
/**********************************/
CREATE TABLE topic(
		topicno 		INT 						NOT NULL PRIMARY KEY,
		topic 			VARCHAR(50) 				NOT NULL,
		twriter 		VARCHAR(20),
		tdate 			DATE,
		tvisible 		CHARACTER(1) 	DEFAULT 'Y'	NOT NULL,
		tseqno 			INT 			DEFAULT 0	NOT NULL,
		chapterno 		INT 						NOT NULL,
  FOREIGN KEY (chapterno) REFERENCES chapter (chapterno)
);
