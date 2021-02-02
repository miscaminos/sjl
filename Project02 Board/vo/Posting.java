package com.encore.vo;

import java.sql.Date;

public class Posting {
	private int num;
	private String writer;
	private Date w_date;
	private String title;
	private String content;
	
	public Posting() {
	}

	public Posting(int num, String writer, Date w_date, String title, String content) {
		this.num = num;
		this.writer = writer;
		this.w_date = w_date;
		this.title = title;
		this.content = content;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Date getW_date() {
		return w_date;
	}

	public void setW_date(Date w_date) {
		this.w_date = w_date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Board [num=" + num + ", writer=" + writer + ", w_date=" + w_date + ", title=" + title + ", content="
				+ content + "]";
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj!=null && obj instanceof Posting) {
			if(((Posting)obj).num==num) {
				return true;
			}
		}
		return super.equals(obj);
	}
	
	
}
