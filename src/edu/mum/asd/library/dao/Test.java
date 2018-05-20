package edu.mum.asd.library.dao;

import java.util.ArrayList;

import edu.mum.asd.library.model.Admin;
import edu.mum.asd.library.model.Librarian;
import edu.mum.asd.library.model.Student;

public class Test {

	public static void main(String[] args) {

		// successfully passed this test for studentDao's save method
		//StudentDao studentDao = new StudentDao();
		Student student = new Student();

		student.setFirstName("david");
		student.setLastName("beckham");
		student.setEmail("davidbeck@mail.com");
		student.setPassword("mafrel");
		student.setPhone("1231213");
		student.setAddress("fairfield iowa");
		student.setApprovedBy(null);
		student.setEligible(true);
		//studentDao.save(student);

		// successfully passed this test for studentDao's save method
		//LibrarianDao librarianDao = new LibrarianDao();
		Librarian librarian = new Librarian();
		librarian.setFirstName("david");
		librarian.setLastName("beckham");
		librarian.setEmail("davidbeck@mail.com");
		librarian.setPassword("mafrel");
		librarian.setPhone("1231213");
		librarian.setAddress("fairfield iowa");
	//	librarianDao.save(librarian);

		// successfully passed this test for studentDao's save method
		//AdminDao adminDao = new AdminDao();
		Admin admin = new Admin();
		admin.setFirstName("david");
		admin.setLastName("beckham");
		admin.setEmail("davidbeck@mail.com");
		admin.setPassword("mafrel");
		admin.setPhone("1231213");
		admin.setAddress("fairfield iowa");
		//adminDao.save(admin);
		
		
		//LibrarianDao libDao = new LibrarianDao();
		LibrarianDao librarianDao = new LibrarianDao();
		ArrayList<Librarian> libs = new ArrayList<Librarian>();
		libs = (ArrayList<Librarian>) librarianDao.view();
		for(Librarian lib : libs) {
			System.out.println(lib.getFirstName());
		}

	}
}
