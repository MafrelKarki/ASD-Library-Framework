package edu.mum.asd.library.model;


/**
 * @author Mafrel
 * @Purpose implementing visitor design pattern for notifying Student
 */
public class StudentVisitor implements Visitable {

	@Override
	public void notifyUser(User user) {
		Student student = (Student)user;
		System.out.println("Sent email to a Student -> "+ student.getFirstName()+" "+student.getLastName());
	}

}
