package edu.mum.asd.library.model;

public class BorrowedState implements ItemState {

	private Loan loan;
	@Override
	public void changeItemState() {
		loan.setReturnStatus("NO");
		loan.setCurrentState(new BorrowedState());
	}

	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}

}
