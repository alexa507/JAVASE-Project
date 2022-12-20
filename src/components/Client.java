package components;

import java.util.concurrent.atomic.AtomicInteger;
//1.1.1 Creation of the client class
public class Client {

	private static final AtomicInteger count = new AtomicInteger(0); 
	private String name;
	private String firstName;
	private int clientNumber = 0;
	
	public Client(String name, String firstName) {
		super();
		this.name = name;
		this.firstName = firstName;
		this.clientNumber = count.incrementAndGet();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(int clientNumber) {
		this.clientNumber = clientNumber;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Client [name=");
		builder.append(name);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", clientNumber=");
		builder.append(clientNumber);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
