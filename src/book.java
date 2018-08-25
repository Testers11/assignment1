import java.io.Serializable;


//Review_1.0 - Add the Method header comments for all methods
@SuppressWarnings("serial")
public class Book implements Serializable { // change_1.01 class name book has changes to Book
	
	private String title; // change_1.02 veriable name T has changed to title
	private String author; // change_1.03 veriable name A has changed to author
	private String callNo; // change_1.04 veriable name C has changed to callNo
	private int id; // change_1.05 veriable name ID has changed to id
	
	private enum STATE { AVAILABLE, ON_LOAN, DAMAGED, RESERVED };
	private STATE state;
	
	
	public Book(String author, String title, String callNo, int id) {  // change_1.06 class name book has changed to Book
		this.author = author; // change_1.07 this.A has changed to this.author
		this.title = title; // change_1.08 this.T has changed to this.title
		this.callNo = callNo; // change_1.09 this.C has changed to this.callNo
		this.id = id; // change_1.10 this.ID has changed to this.id
		this.state = STATE.AVAILABLE;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Book: ").append(id).append("\n") // change_1.11 ID has changed to id.
		  .append("  Title:  ").append(title).append("\n") // change_1.12 T has changed to title.
		  .append("  Author: ").append(author).append("\n") // change_1.13 A has changed to author.
		  .append("  CallNo: ").append(callNo).append("\n") // change_1.14 C has changed to callNo .
		  .append("  State:  ").append(state);
		
		return sb.toString();
	}

	public Integer getId() { // change_1.15 method name ID has changed to getId
		return id; // change_1.16 variable name ID has changed to id.
	}

	public String getTitle() { // change_1.17 method name Title has changed to getTitle
		return title; // change_1.18 variable name T has changed to title
	}


	
	public boolean isAvailable() { // change_1.19 method name Available has changed to isAvailable.
		return state == STATE.AVAILABLE;
	}

	
	public boolean isOnLoan() { // change_1.20 method name On_loan has changed to isOnLoan.
		return state == STATE.ON_LOAN;
	}

	
	public boolean isDamaged() { // change_1.21 method name Damaged has changed to isDamaged.
		return state == STATE.DAMAGED;
	}

	
	public void borrowBook() { // change_1.22 method name Borrow has changed to borrowBook
		if (state.equals(STATE.AVAILABLE)) {
			state = STATE.ON_LOAN;
		}
		else {
			throw new RuntimeException(String.format("Book: cannot borrow while book is in state: %s", state));
		}
		
	}


	public void returnBook(boolean DAMAGED) { // change_1.23 method name Return has changed to returnBook.
		if (state.equals(STATE.ON_LOAN)) {
			if (DAMAGED) {
				state = STATE.DAMAGED;
			}
			else {
				state = STATE.AVAILABLE;
			}
		}
		else {
			throw new RuntimeException(String.format("Book: cannot Return while book is in state: %s", state));
		}		
	}

	
	public void repairBook() { // change_1.24 method name Repair has changed to repairBook.
		if (state.equals(STATE.DAMAGED)) {
			state = STATE.AVAILABLE;
		}
		else {
			throw new RuntimeException(String.format("Book: cannot repair while book is in state: %s", state));
		}
	}


}
