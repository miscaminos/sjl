/**
 * @author SojungLee
 * @since JDK 1.8.271 
 * @version BookManager version1
 */

package com.encore.service;

import com.encore.parent.Book;
import com.encore.child.Magazine;

public class BookManager {
	
	private int numberOfBooks;
	private int idx;
	private Book[] list;
	
	public BookManager() { }
	
	/**
	 * @param numberOfBooks 책 배열이 갖고있는 책의 갯수
	 * @param size 책 배열의 최대 사이즈
	 */
	public BookManager(int numberOfBooks, int size) {
		this.list= new Book[size]; //max size만큼의 list size확보
		this.numberOfBooks = numberOfBooks;//현재 list내의 책수
		this.idx=0;
	}
	
	/**
	 * @return 현재 책 배열이 갖고있는 책의 갯수
	 */
	public int getNumberOfBooks() {
		return numberOfBooks;
	}
	
	/**
	 * @param bk 책 배열에 넣으려는 Book type의 객체
	 */
	public void insertBook(Book bk) {
		if(idx<list.length)	
			list[idx++] = bk;
	}
	
	/**
	 * @return 현재 insert된 모든  Book type 객체를 담고있는 책 배열
	 */
	public Book[] getAllBook() {
		return list;
	}
	
	/**
	 * @param bk Book type의 객체를 담고있는 배열
	 * @return 찾은 Magazine type 객체를 담고있는 배열
	 */
	public Magazine[] getAllMagazine(Book[] bk) {
		Magazine[] temp = new Magazine[list.length];
		int i=0;
		for (Book b:bk) {
			if(b==null) continue;
			if(b instanceof Magazine) temp[i++] = ((Magazine)b);
		}
		return temp;
	}
	
	/**
	 * @param isbn 책 ISBN 문자열
	 * @return 인자값 ISBN에 해당하는 Book type 객체
	 */
	public Book searchBookByIsbn(String isbn) {
		Book bk=null;
		for (int i=0; i<list.length; i++) {
			if(list[i]==null) continue;
			if(list[i].getIsbn().equals(isbn)) bk=list[i];
		}
		return bk;
	}
	
	/**
	 * @param title 책 Title 문자열
	 * @return 인자값 title에 해당하는 Book type의 배열
	 */
	public Book[] searchBookByTitle(String title) {
		Book[] temp= new Book[list.length];
		for (int i=0; i<list.length; i++) {
			if(list[i]==null) continue;
			if(list[i].getTitle().equals(title)) {
				temp[i]=list[i];
			}
		}
		return temp;
	}
	
	/**
	 * @param publisher 책 publisher 문자열
	 * @return 인자값 publisher에 해당하는 Book type의 배열
	 */
	public Book[] searchBookByPublisher(String publisher) {
		Book[] temp= new Book[list.length];
		for (int i=0; i<list.length; i++) {
			if(list[i]==null) continue;
			if(list[i].getPublisher().equals(publisher)) {
				temp[i]=list[i];
			}
		}
		return temp;
	}
	
	/**
	 * @param price 책 price
	 * @return 인자값 price에 해당하는 Book type의 배열
	 */
	public Book[] searchBookByPrice(double price) {
		Book[] temp= new Book[list.length];
		for (int i=0; i<list.length; i++) {
			if(list[i]==null) continue;
			if(list[i].getPrice()==price) {
				temp[i]=list[i];
			}
		}
		return temp;
	}
	
	/**
	 * @return 모든 책의 가격의 합
	 */
	public double getSumPriceOfBooks() {
		double sum=0;
		for (int i=0; i<list.length; i++) {
			if(list[i]==null) continue;
			sum+=list[i].getPrice();
		}
		return sum;
	}
	
	/**
	 * @return 모든 책의 평균가격
	 */
	public double getAveragePriceOfBooks() {
		double avg=0;
		avg= this.getSumPriceOfBooks()/numberOfBooks;
		return avg;
	}
}
