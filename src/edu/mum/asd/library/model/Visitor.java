package edu.mum.asd.library.model;


/**
 * @author Mafrel
 * @Purpose implementing visitor design pattern for notifying users
 */
public interface Visitor {
	public void visit(Librarian librarian);
	public void visit(Student student);
}
