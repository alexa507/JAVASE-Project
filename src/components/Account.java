package components;

import java.util.concurrent.atomic.AtomicInteger;
//1.2.1 Creation of the account class
public abstract class Account {

	private static final AtomicInteger count = new AtomicInteger(0); 
	protected String label;
	protected double balance;
	protected int accountNumber = 0;
	Client accountClient;
	
	public Account(String label, Client accountClient) {
		super();
		this.label = label;
		this.accountClient = accountClient;
		this.accountNumber = count.incrementAndGet();
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public double getBalance() {
		return balance;
	}

	//1.3.5 Updating accounts
	public void setBalance(Flow flow) {
		if(flow.getIdentifier() == "Debit") {
			balance = balance - flow.getAmount();
		}
		if(flow.getIdentifier() == "Credit") {
			balance = balance + flow.getAmount();
		}
		if(flow.getIdentifier() == "Transfer") {
			if(accountNumber == flow.getTargetAccountNumber()) {
				balance = balance + flow.getAmount();
			}
			int accountNumberIssue = ((Transfer)flow).getAccountNumberIssue();
			if(accountNumber == accountNumberIssue) {
				balance = balance - flow.getAmount();
			}
		}
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Client getAccountClient() {
		return accountClient;
	}

	public void setAccountClient(Client accountClient) {
		this.accountClient = accountClient;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Account [label=");
		builder.append(label);
		builder.append(", balance=");
		builder.append(balance);
		builder.append(", accountNumber=");
		builder.append(accountNumber);
		builder.append(", accountClient=");
		builder.append(accountClient);
		builder.append("]");
		return builder.toString();
	}
	
	
}
