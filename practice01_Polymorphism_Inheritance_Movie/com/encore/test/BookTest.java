package com.encore.test;

import com.encore.parent.Book;
import com.encore.child.Magazine;
import com.encore.service.BookManager;

public class BookTest {

	public static void main(String[] args) {
		int MAX_SIZE=10;
		
		Book[] books = {new Book("ABC111", "Everyone's Pilates", "Abe", "Today's Exercise", 15.5, "pilates"),
						new Magazine("ABC222", "Tennis Tomorrow", "Bob", "US Open Association", 13.5, "tennis", 2020, 12),
						new Magazine("ABC333", "Football League", "Cate", "FC", 15.5, "Totnum Hotspur", 2021, 1),
						new Book("ABC444", "YogaLife", "Daniel", "India", 17.5, "yoga"),
						new Magazine("ABC555", "Football League", "Cate", "FD", 15.5, "Totnum Hotspur", 2021, 2)};
		
		BookManager manager = new BookManager(books.length,MAX_SIZE);
		for (Book bk:books) {
			manager.insertBook(bk); //BookManager class내의 list[]에 bk 주입
		}
		
		Book[] bk = manager.getAllBook();
		System.out.println("=====Current Total Book List=====");
		printBooks(bk);
		
		Magazine[] mg = manager.getAllMagazine(bk);
		System.out.println("=====Magazine Selection List=====");
		printBooks(mg);
		
		Book bookByIsbn = manager.searchBookByIsbn("ABC111");
		System.out.println("=====search by ISBN=====");
		System.out.println(bookByIsbn.toString());
		
		Book[] bookByTitle = manager.searchBookByTitle("Football League");
		System.out.println("=====search by Title=====");
		printBooks(bookByTitle);
		
		Book[] bookByPublisher = manager.searchBookByPublisher("US Open Association");
		System.out.println("=====search by Publisher=====");
		printBooks(bookByPublisher);
		
		Book[] bookByPrice = manager.searchBookByPrice(15.5);
		System.out.println("=====search by Price=====");
		printBooks(bookByPrice);
		
				
		double sum = manager.getSumPriceOfBooks();
		System.out.println("Total Price: "+sum);
		
		double avg = manager.getAveragePriceOfBooks();
		System.out.println("Average Price:"+avg);
		
	}
	
	static void printBooks(Book[] books) {
		for (Book b:books) {
			if(b==null) continue;
			System.out.println(b);
		}
		System.out.println();
	}

}
