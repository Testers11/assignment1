import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class Loan implements Serializable { // changed loan to Loan Class name should be in capital

	public static enum LOAN_STATE { CURRENT, OVER_DUE, DISCHARGED };

	private int loanId;//change 2.01_
	private Book book; //change 2.02_class name to Book and variable B to book
	private Member member; //change 2.03_ class name to Member and variable M to member
	private Date dueDate; //change 2.04_ changed variable D to dueDate
	private LOAN_STATE state;


	public Loan(int loanId, book book, member member, Date dueDate) { //change 2.05_ changed loan to Loan..
		this.loanId = loanId; //change 2.06_ changed ID to loanId
		this.book = book; //change 2.07_ changed B to book
		this.member = member; //change 2.08_ changed M to member
		this.dueDate = dueDate; //change 2.09_changed date to dueDate
		this.state = LOAN_STATE.CURRENT;
	}


	public void checkOverDue() {
		if (state == LOAN_STATE.CURRENT &&
			Calendar.getInstance().Date().after(dueDate)) { //change 2.10_changed D to dueDate
			this.state = LOAN_STATE.OVER_DUE;
		}
	}


	public boolean isOverDue() {
		return state == LOAN_STATE.OVER_DUE;
	}


	public Integer getId() {
		return loanId;  //change 2.11_changed ID to loanId
	}


	public Date getDueDate() {
		return dueDate; //change 2.12_changed D to dueDate
	}


	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Review 2.1 sdf needs to be changed into simpleDateFormat

		StringBuilder sb = new StringBuilder();			// Review 2.2 sb needs to be changed into stringBuilder
		sb.append("Loan:  ").append(loanId).append("\n") //change 2.13_changed ID to loanId
														// Review 2.3 sb needs to be changed into stringBuilder
		  .append("  Borrower ").append(member.getId()).append(" : ") //change 2.14_ changed M to member
		  .append(member.getLastName()).append(", ").append(member.getFirstName()).append("\n") //change 2.15_changed M to member
		  .append("  Book ").append(book.dueDate()).append(" : " ) //change 2.16_changed B to book and D to dueDate
		  .append(book.Title()).append("\n") //change 2.17_ changed B to book 
		  .append("  DueDate: ").append(sdf.format(dueDate)).append("\n") //change 2.18_changed D to dueDate
		  .append("  State: ").append(state);
		return sb.toString();		// Review 2.4 sb needs to be changed into stringBuilder
	}


	public member Member() {	// Review 2.5 member needs to be changed into Member and Member into member
		return membetr; //change 2.19_changed M to member
	}


	public book Book() {		// Review 2.6 book needs to be changed into Book and Book into book
		return book; //change 2.20_changed B to book
	}


	public void Loan() {
		state = LOAN_STATE.DISCHARGED;
	}

}
