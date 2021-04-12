/**********************************/
/* Table Name: chapters */
/**********************************/
CREATE TABLE chapter(
		chapterno 		INT 					 		NOT NULL PRIMARY KEY,
		chapter 		VARCHAR(50) 					NOT NULL,
		cwriter 		VARCHAR(20) 					NOT NULL,
		cdate 			DATE 							NOT NULL,
		cvisible 		CHARACTER(1) 	DEFAULT 'Y'		NOT NULL,
		cseqno 			INT 			DEFAULT 0		NOT NULL
);

