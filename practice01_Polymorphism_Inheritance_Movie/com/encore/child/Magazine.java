package com.encore.child;

import com.encore.parent.Book;

public class Magazine extends Book {
	private int year;
	private int month;
	
	public Magazine() { }
	
	public Magazine(String isbn, String title, String author, String publisher, double price, String desc, int year, int month) {
		super(isbn,title,author,publisher,price,desc);
		this.year = year;
		this.month = month;
	}

	@Override
	public String toString() {
		return super.toString()+"Magazine [year=" + year + ", month=" + month + "]";
	}

	public void setYear(int year) {
		this.year = year;
	}
	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}
	public int getMonth() {
		return month;
	}
	
	
	
}
