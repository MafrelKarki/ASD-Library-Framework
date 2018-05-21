package edu.mum.asd.library.model;

import java.util.Iterator;
import java.util.List;


/**
 * @author Mafrel
 * @purpose Implementing Iterator design pattern to Iterate through the books
 */
public class BookIterator implements Iterator<Book> {

	private List<Book> bookList;
	int position;
	

	public BookIterator(List<Book> bookList) {
		super();
		this.bookList = bookList;
	}

	@Override
	public boolean hasNext() {
		if (position >= bookList.size() || bookList.get(position) == null)
			return false;
		return true;
	}

	@Override
	public Book next() {
		return bookList.get(position++);
	}

}
