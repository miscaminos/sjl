package com.study.contents;

import lombok.Data;

@Data
public class ContentsDTO {
	private int contentsno;
	private String title;
	private String contents;
	private String cdate;
	private char visible;
	private int seqno;
	
}
