package components;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
//1.3.2 Creation of the Flow class
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Debit.class, name = "Debit"),
    @JsonSubTypes.Type(value = Transfer.class, name = "Transfer"),
    @JsonSubTypes.Type(value = Credit.class, name = "Credit") }
)
public abstract class Flow {

	private String comment;
	private String identifier;
	private double amount;
	private int targetAccountNumber;
	private boolean effect;
	private LocalDate dateOfFlow = LocalDate.now().plusDays(2);
	
	public Flow(String comment, String identifier, double amount, int targetAccountNumber, boolean effect) {
		super();
		this.comment = comment;
		this.identifier = identifier;
		this.amount = amount;
		this.targetAccountNumber = targetAccountNumber;
		this.effect = effect;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getTargetAccountNumber() {
		return targetAccountNumber;
	}

	public void setTargetAccountNumber(int targetAccountNumber) {
		this.targetAccountNumber = targetAccountNumber;
	}

	public boolean isEffect() {
		return effect;
	}

	public void setEffect(boolean effect) {
		this.effect = effect;
	}

	public LocalDate getDateOfFlow() {
		return dateOfFlow;
	}
	
}
