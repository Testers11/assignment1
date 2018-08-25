import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class Member implements Serializable {					// Change cycle 1.1 - Change class member into Member
	private String lastName;									// Change cycle 1.2 - change LN into lastName
	private String firstName;									// Change cycle 1.3 - change FN into firstName
	private String email;										// Change cycle 1.4 - change EM into email
	private int phoneNo;										// Change cycle 1.5 - change PN into phoneNo
	private int id;												// Change cycle 1.6 - change ID into id
	private double fines;										// Change cycle 1.7 - change FINES into fines
	
	private Map<Integer, loan> LNS;

	
	public Member(String lastName, String firstName, String email, int phoneNo, int id) { // Change cycle 1.8 - Change class member into Member
		this.lastName = lastName														 // Change cycle 1.9 - change LN into lastName
		this.firstName = firstName;														// Change cycle 1.10 - change FN into firstName
		this.email = email;																// Change cycle 1.11- change EM into email
		this.phoneNo = phoneNo;															// Change cycle 1.12- change PN into phoneNo
		this.id = id;																	// Change cycle 1.13- change ID into id
		
		this.LNS = new HashMap<>();
	}

	
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();								// Change cycle 1.14- change sb into stringBuilder
		stringBuilder.append("Member:  ").append(ID).append("\n")						// Change cycle 1.15- change sb into stringBuilder
		  .append("  Name:  ").append(lastName).append(", ").append(firstName).append("\n")	// Change cycle 1.16 - change LN into lastName and FN into firstName
	private String firstName;																	
		  .append("  Email: ").append(email).append("\n")								// Change cycle 1.17 - change EM into email
		  .append("  Phone: ").append(phoneNo)											// Change cycle 1.18 - change PN into phoneNo
		  .append("\n")
		  .append(String.format("  Fines Owed :  $%.2f", fines))						// Change cycle 1.19 - change FINES into fines
		  .append("\n");
		
		for (Loan loan : LNS.values()) {										// Change cycle 1.20 - change loan into Loan
			stringBuilder.append(loan).append("\n");							// Change cycle 1.21- change sb into stringBuilder
		}		  
		return stringBuilder.toString();										// Change cycle 1.22- change sb into stringBuilder
	}

	
	public int getId() {
		return id;												// Change cycle 1.23- change ID into id
	}

	
	public List<loan> getLoans() {
		return new ArrayList<loan>(LNS.values());
	}

	
	public int getNumberOfCurrentLoans() {
		return LNS.size();
	}

	
	public double getFinesOwed() {
		return FINES;
	}

	
	public void takeOutLoan(Loan loan) {					// Change cycle 1.24 - change loan into Loan
		if (!LNS.containsKey(loan.getId())) {
			LNS.put(loan.getId(), loan);
		}
		else {
			throw new RuntimeException("Duplicate loan added to member");
		}		
	}

	
	public String getLastName() {
		return lastName;									// Change cycle 1.25 - change LN into lastName
	}

	
	public String getFirstName() {
		return firstName;								// Change cycle 1.26 - change FN into firstName
	}


	public void addFine(double fine) {
		fines += fine;								// Change cycle 1.27 - change FINES into fines
	}
	
	public double payFine(double amount) {
		if (amount < 0) {
			throw new RuntimeException("Member.payFine: amount must be positive");
		}
		double change = 0;
		if (amount > fines) {						// Change cycle 1.28 - change FINES into fines
			change = amount - fines;			// Change cycle 1.29 - change FINES into fines
			fines = 0;							// Change cycle 1.30 - change FINES into fines
		}
		else {
			fines -= amount;				// Change cycle 1.31 - change FINES into fines
		}
		return change;
	}


	public void dischargeLoan(Loan loan) {			// Change cycle 1.32 - change loan into Loan
		if (LNS.containsKey(loan.getId())) {
			LNS.remove(loan.getId());
		}
		else {
			throw new RuntimeException("No such loan held by member");
		}		
	}

}
