import java.util.Scanner;


public class ReturnBookControl {

	//change3.00_ enums for book status
	private ReturnBookUI UserInterface; // change 3.01_ changed ui to userinterface
							//Review 3.1 UserInterface needs to be changed into userInterface
	private enum CONTROL_STATE { INITIALISED, READY, INSPECTING };
	private CONTROL_STATE state;
	
	private library library; //Review 3.2 library(first) needs to be changed into Library
	private loan currentLoan; //Review 3.3 loan needs to be changed into Loan
	

	// comment 3.01_ constructor for ReturnbookControl
	public ReturnBookControl() {
		this.library = library.INSTANCE();
		state = CONTROL_STATE.INITIALISED;
	}
	
	// comment 3.02_ set UserInterface
	public void set(ReturnBookUI UserInterface) { // change 3.02_ changed ui to UserInterface
										//Review 3.4 ReturnBookUI and  UserInterface need to be changed into returnBookUI and userInterface respectively
		if (!state.equals(CONTROL_STATE.INITIALISED)) {
			throw new RuntimeException("ReturnBookControl: cannot call setUI except in INITIALISED state");
		}	
		this.UserInterface = UserInterface; //change 3.03_ changed ui to UserInterface
											//Review 3.5 UserInterface needs to be changed into userInterface
		ui.setState(ReturnBookUI.UI_STATE.READY);		//Review 3.6 ui needs to be changed into userInterface
		state = CONTROL_STATE.READY;		
	}


	// comment 3.03_ method for scan book
	public void bookScanned(int bookId) {
		if (!state.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("ReturnBookControl: cannot call bookScanned except in READY state");
		}	
		book currentBook = library.Book(bookId);
		
		if (currentBook == null) {
			ui.display("Invalid Book Id");		//Review 3.7 ui needs to be changed into userInterface
			return;
		}
		if (!currentBook.On_loan()) {
			UserInterface.display("Book has not been borrowed");  // change 3.04_ changed ui to UserInterface
			return;
		}		
		currentLoan = library.getLoanByBookId(bookId);	
		double overDueFine = 0.0;
		if (currentLoan.isOverDue()) {
			overDueFine = library.calculateOverDueFine(currentLoan);
		}
		UserInterface.display("Inspecting"); // change 3.05_ changed ui to UserInterface
		UserInterface.display(currentBook.toString()); // change 3.06_ changed ui to UserInterface
		UserInterface.display(currentLoan.toString()); // change 3.07_ changed ui to UserInterface
		
		if (currentLoan.isOverDue()) {
			UserInterface.display(String.format("\nOverdue fine : $%.2f", overDueFine)); // change 3.08_ changed ui to UserInterface
		}
		UserInterface.setState(ReturnBookUI.UI_STATE.INSPECTING); // change 3.09_ changed ui to UserInterface
		state = CONTROL_STATE.INSPECTING;		
	}


	// comment 3.04_ method to show scanning progress message
	public void scanningComplete() {
		if (!state.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("ReturnBookControl: cannot call scanningComplete except in READY state");
		}	
		UserInterface.setState(ReturnBookUI.UI_STATE.COMPLETED); // change 3.10_ changed ui to UserInterface		
	}


	public void dischargeLoan(boolean isDamaged) {
		if (!state.equals(CONTROL_STATE.INSPECTING)) {
			throw new RuntimeException("ReturnBookControl: cannot call dischargeLoan except in INSPECTING state");
		}	
		library.dischargeLoan(currentLoan, isDamaged);
		currentLoan = null;
		UserInterface.setState(ReturnBookUI.UI_STATE.READY); // change 3.11_ changed ui to UserInterface
		state = CONTROL_STATE.READY;				
	}


}
