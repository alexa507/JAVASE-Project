package components;

import java.time.LocalDate;
//1.3.3 Creation of the Transfert class
public class Transfer extends Flow {

	int accountNumberIssue;

	public Transfer(String comment, String identifier, float amount, int targetAccountNumber, boolean effect) {
		super(comment, identifier, amount, targetAccountNumber, effect);
		// TODO Auto-generated constructor stub
	}

	public int getAccountNumberIssue() {
		return accountNumberIssue;
	}

	public void setAccountNumberIssue(int accountNumberIssue) {
		this.accountNumberIssue = accountNumberIssue;
	}
}
