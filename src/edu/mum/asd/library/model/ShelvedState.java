package edu.mum.asd.library.model;

public class ShelvedState implements ItemState {

	private Loan loan;
	
	public ShelvedState(Loan loan) {
		this.loan=loan;
	}

	@Override
	public void changeItemState() {
		loan.setReturnStatus("YES");
		loan.setCurrentState(new ShelvedState(loan));
	}

	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}

}
