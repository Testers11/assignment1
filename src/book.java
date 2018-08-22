import java.io.Serializable;


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
		  .append("  CallNo: ").append(C).append("\n") // change_1.14 C has changed to callNo .
		  .append("  State:  ").append(state);
		
		return sb.toString();
	}

	public Integer ID() {
		return ID;
	}

	public String Title() {
		return T;
	}


	
	public boolean Available() {
		return state == STATE.AVAILABLE;
	}

	
	public boolean On_loan() {
		return state == STATE.ON_LOAN;
	}

	
	public boolean Damaged() {
		return state == STATE.DAMAGED;
	}

	
	public void Borrow() {
		if (state.equals(STATE.AVAILABLE)) {
			state = STATE.ON_LOAN;
		}
		else {
			throw new RuntimeException(String.format("Book: cannot borrow while book is in state: %s", state));
		}
		
	}


	public void Return(boolean DAMAGED) {
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

	
	public void Repair() {
		if (state.equals(STATE.DAMAGED)) {
			state = STATE.AVAILABLE;
		}
		else {
			throw new RuntimeException(String.format("Book: cannot repair while book is in state: %s", state));
		}
	}


}
