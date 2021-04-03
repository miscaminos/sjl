/**********************************/
/* Table Name: chapters */
/**********************************/
CREATE TABLE chapter(
		chapterno INT DEFAULT 0 			NOT NULL PRIMARY KEY,
		chapter VARCHAR(50) 				NOT NULL,
		cwriter VARCHAR(20) 				NOT NULL,
		cdate DATE 							NOT NULL,
		visible CHAR(1) DEFAULT 'Y'			NOT NULL,
		seqno INT 		DEFAULT 0			NOT NULL
);

