package edu.mum.asd.library.model;

public class Test {
	
	public static void main(String[] args) {
		
		Visitor visitor = new UserVisitor();
		Librarian lib = new Librarian();
		Student std = new Student();
		visitor.visit(lib);
		visitor.visit(std);
	}
	
}
