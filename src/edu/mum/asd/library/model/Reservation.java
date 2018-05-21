package edu.mum.asd.library.model;

public class Reservation {

	private long id;
	private long studentId;
	private String bookCallNO;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public String getBookCallNO() {
		return bookCallNO;
	}

	public void setBookCallNO(String bookCallNO) {
		this.bookCallNO = bookCallNO;
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", studentId=" + studentId + ", bookCallNO=" + bookCallNO + "]";
	}

	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reservation(long id, long studentId, String bookCallNO) {
		super();
		this.id = id;
		this.studentId = studentId;
		this.bookCallNO = bookCallNO;
	}

}
