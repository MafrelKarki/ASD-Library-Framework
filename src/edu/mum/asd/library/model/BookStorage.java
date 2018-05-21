package edu.mum.asd.library.model;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Mafrel
 * @purpose Implementing Iterator design pattern to Iterate through the books
 */
public class BookStorage {
	
	private List<Book> bookList = new ArrayList<>();
	
	public void addBook(Book book) {
		bookList.add(book);
	}
	
	public List<Book> getBooks(){
		return this.bookList;
	}
}
