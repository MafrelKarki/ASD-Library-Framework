package edu.mum.asd.library.model;


/**
 * @author Mafrel
 * @Purpose implementing visitor design pattern for notifying both users
 */
public class UserVisitor implements Visitor {

	Visitable libVisit = new LibrarianVisitor();
	Visitable stuVisit = new StudentVisitor();
	
	@Override
	public void visit(Librarian librarian) {
		// TODO Auto-generated method stub
		libVisit.notifyUser(librarian);
	}

	@Override
	public void visit(Student student) {
		// TODO Auto-generated method stub
		stuVisit.notifyUser(student);
	}

}
