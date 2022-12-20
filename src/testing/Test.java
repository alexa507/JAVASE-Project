package testing;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.fasterxml.jackson.databind.ObjectMapper;

import components.Account;
import components.Client;
import components.Credit;
import components.CurrentAccount;
import components.Debit;
import components.Flow;
import components.SavingsAccount;
import components.Transfer;
//1.1.2 Creation of main class for tests
public class Test {

	//1.1.2 Creation of main class for tests (Creation of clients array)
	static List<Client> clients = new ArrayList<Client>();
	//1.2.3 Creation of the table accounts
	static List<Account> accounts = new ArrayList<Account>();
	//1.3.4 Creation of the flows array
	static List<Flow> flows = new ArrayList<Flow>();
	
	//1.3.1 Adaptation of the table of accounts
	static Hashtable<Integer, Account> ht = new Hashtable<Integer, Account>();

	public static Hashtable<Integer, Account> hashtableReturn(List<Account> accounts) {
		for (Account acc : accounts) {
			ht.put(acc.getAccountNumber(), acc);
		}
		return ht;
	}

	//1.1.2 Creation of main class for tests (Loading method for clients array)
	public static List<Client> loadingClients(int clientNumber) {
		for (int i = 1; i <= clientNumber; i++) {
			clients.add(new Client("name" + i, "fistName" + i));
		}
		return clients;
	}
	//1.1.2 Creation of main class for tests (Display method for clients array)
	public static void displayClients(List<Client> clients) {
		String s = clients.stream().map(Object::toString).collect(Collectors.joining(","));
		System.out.println(s);
	}
	
	//1.2.3  Creation of the table accounts (Loading method for accounts array)
	public static List<Account> loadingAccounts(List<Client> clients) {
		for (Client client : clients) {
			SavingsAccount savingsAcc = new SavingsAccount("Savings", client);
			accounts.add(savingsAcc);
			CurrentAccount currentAcc = new CurrentAccount("Current", client);
			accounts.add(currentAcc);
		}
		return accounts;
	}
	//1.2.3  Creation of the table accounts (Display method for accounts array)
	public static void displayAccounts(List<Account> accounts) {
		String s = accounts.stream().map(Object::toString).collect(Collectors.joining(","));
		System.out.println(s);
	}

	//1.3.4 Creation of the flows array (Loading method for the flow array)
	public static List<Flow> loadingFlows(List<Account> accounts) {
		for (Account account : accounts) {
			if (account.getAccountNumber() == 1) {
				Debit debit1 = new Debit("Comment", "Debit", 50, 1, false);
				flows.add(debit1);
			}

			Credit creditAll = new Credit("Comment Credit", "Credit", 100.50, account.getAccountNumber(), false);
			flows.add(creditAll);

			if (account.getLabel() == "Savings") {
				Credit creditS = new Credit("Comment Credit Savings", "Credit", 1500, account.getAccountNumber(),
						false);
				flows.add(creditS);
			}
		}

		Transfer transfer1 = new Transfer("Comment Transfer 1-2", "Transfer", 50, 2, false);
		transfer1.setAccountNumberIssue(1);
		flows.add(transfer1);

		return flows;
	}

	//1.3.5 Updating accounts
	public static void updateAccounts(List<Flow> flows, Hashtable<Integer, Account> ht) {
		Set<Integer> accountIdentifiers = ht.keySet();
		for (Flow flow : flows) {
			for (Integer key : accountIdentifiers) {
				if (flow.getTargetAccountNumber() == key) {
					ht.get(key).setBalance(flow);
				}
			}
		}

		Predicate<Double> lesserthan = i -> (i < 0);
		ht.keySet().stream().forEach(key -> {
			if (lesserthan.test(ht.get(key).getBalance())) {
				Optional<Account> maybeAccount = Optional.of(ht.get(key));

				if (maybeAccount.isPresent()) {
					System.out.println(
							"Account Number: " + maybeAccount.get().getAccountNumber() + " has nagative balance.");
				}
			}
		});

	}

	public static void displayMap(Hashtable<Integer, Account> ht) {
		ht.values().stream().sorted(Comparator.comparing(Account::getBalance)).forEach(acc -> {
			System.out.println("Account: " + acc.getAccountNumber() + " with balance: " + acc.getBalance());
		});
	}

	//2.1 JSON file of flows
	public static void loadingFlowsFromJSON() {
		Path path = Paths.get("src/resources/JSONflows.json");
		ObjectMapper objectMapper = new ObjectMapper();
		try {
//		 
//		  String contents = Files.readString(path);
//		  List<Flow> flows = Arrays.asList(objectMapper.readValue(contents, Flow[].class));
//		  //Read from the stream
//		  for(Flow flow:flows){//for each line of content in contents
//		    System.out.println(flow.toString());// print the line
//		  }

			List contents = Files.readAllLines(path);
			for (Object content : contents) {
				System.out.println(content);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	//2.2 XML file of account
	public static void loadingAccountsFromXML() {
		Path path = Paths.get("src/resources/XMLaccounts.xml");
		try {

//		  String contents = Files.readString(path);
//		  StringReader sr = new StringReader(contents);
//		  JAXBContext jaxbContext = JAXBContext.newInstance(Account.class);
//		  Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//		  Account response = (Account) unmarshaller.unmarshal(sr); 
		  
			List contents = Files.readAllLines(path);
			for (Object content : contents) {
				System.out.println(content);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
	}

	public static void main(String[] args) {

		List<Client> clients = loadingClients(3);
		displayClients(clients);
		
		List<Account> accounts = loadingAccounts(clients);
		displayAccounts(accounts);
		
		Hashtable<Integer, Account> ht = hashtableReturn(accounts);
		
		List<Flow> flows = loadingFlows(accounts);
		
		updateAccounts(flows, ht);
		
		displayMap(ht);

		loadingFlowsFromJSON();
		loadingAccountsFromXML();
	}

}
