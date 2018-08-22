import java.util.ArrayList;
import java.util.List;

public class BorrowBookControl {
	
	private BorrowBookUI ui;
	
	private Library library; //change_1.01 Object class name library has changed to Library and object reference variable name L has changed to library
	private Member member; //change_1.02 Object class name member has changed to Member and object reference variable name M has changed to member
	private enum CONTROL_STATE { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED };
	private CONTROL_STATE state;
	
	private List<book> PENDING;
	private List<loan> COMPLETED;
	private Book book; // change_1.03 Object class name book has changed to Book and object reference variable name B has changed to book
	
	
	public BorrowBookControl() {
		this.library = library.INSTANCE(); // change_1.04 variable name L has changed to library as defined above. 
		state = CONTROL_STATE.INITIALISED;
	}
	

	public void setUI(BorrowBookUI ui) {
		if (!state.equals(CONTROL_STATE.INITIALISED)) 
			throw new RuntimeException("BorrowBookControl: cannot call setUI except in INITIALISED state");
			
		this.ui = ui;
		ui.setState(BorrowBookUI.UI_STATE.READY);
		state = CONTROL_STATE.READY;		
	}

		
	public void swiped(int memberId) { // change_1.05 method name Swiped has changed to swipe as first letter should be lowercase
		if (!state.equals(CONTROL_STATE.READY)) 
			throw new RuntimeException("BorrowBookControl: cannot call cardSwiped except in READY state");
			
		member = library.getMember(memberId); // change_1.06 variable names M and L have changed to member and library as defined above. 
		if (member == null) { // change_1.07 variable name M has changed to member.
			ui.display("Invalid memberId");
			return;
		}
		if (library.memberCanBorrow(member)) { // change_1.08 variable names L and M have changed to library and member as defined above.
			PENDING = new ArrayList<>();
			ui.setState(BorrowBookUI.UI_STATE.SCANNING);
			state = CONTROL_STATE.SCANNING; }
		else 
		{
			ui.display("Member cannot borrow at this time");
			ui.setState(BorrowBookUI.UI_STATE.RESTRICTED); }}

	public void Scanned(int bookId) {
		B = null;
		if (!state.equals(CONTROL_STATE.SCANNING)) {
			throw new RuntimeException("BorrowBookControl: cannot call bookScanned except in SCANNING state");
		}	
		B = L.Book(bookId);
		if (B == null) {
			ui.display("Invalid bookId");
			return;
		}
		if (!B.Available()) {
			ui.display("Book cannot be borrowed");
			return;
		}
		PENDING.add(B);
		for (book B : PENDING) {
			ui.display(B.toString());
		}
		if (L.loansRemainingForMember(M) - PENDING.size() == 0) {
			ui.display("Loan limit reached");
			Complete();
		}
	}
	
	
	public void Complete() {
		if (PENDING.size() == 0) {
			cancel();
		}
		else {
			ui.display("\nFinal Borrowing List");
			for (book b : PENDING) {
				ui.display(b.toString());
			}
			COMPLETED = new ArrayList<loan>();
			ui.setState(BorrowBookUI.UI_STATE.FINALISING);
			state = CONTROL_STATE.FINALISING;
		}
	}


	public void commitLoans() {
		if (!state.equals(CONTROL_STATE.FINALISING)) {
			throw new RuntimeException("BorrowBookControl: cannot call commitLoans except in FINALISING state");
		}	
		for (book b : PENDING) {
			loan loan = L.issueLoan(b, M);
			COMPLETED.add(loan);			
		}
		ui.display("Completed Loan Slip");
		for (loan loan : COMPLETED) {
			ui.display(loan.toString());
		}
		ui.setState(BorrowBookUI.UI_STATE.COMPLETED);
		state = CONTROL_STATE.COMPLETED;
	}

	
	public void cancel() {
		ui.setState(BorrowBookUI.UI_STATE.CANCELLED);
		state = CONTROL_STATE.CANCELLED;
	}
	
	
}
