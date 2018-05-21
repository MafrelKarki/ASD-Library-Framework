package edu.mum.asd.library.model;


/**
 * @author Mafrel
 * @Purpose implementing visitor design pattern for notifying Librarian
 */
public class LibrarianVisitor implements Visitable {

	@Override
	public void notifyUser(User user) {
		Librarian lib = (Librarian)user;
		System.out.println("Sent an email to Librarian -> "+ lib.getFirstName());
	}

}
