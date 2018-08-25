// Review-1.01 Add author name

public class FixBookControl {
	// 1.1 Comment Enums for book status
	private FixBookUI userInterface;
	private enum CONTROL_STATE { INITIALISED, READY, FIXING };
	private CONTROL_STATE state;
	
	private library library;
	private book currentBook;

     //1.2 Comment constructor for fix book controler
	public FixBookControl() {
		this.library = library.INSTANCE();
		state = CONTROL_STATE.INITIALISED;
	}
	
	//1.3 Comment set User Interface
	//1.4Changed to meaningfull Method name
	public void setUserInterface(FixBookUI userInterface) {
		if (!state.equals(CONTROL_STATE.INITIALISED)) {
			throw new RuntimeException("FixBookControl: cannot call setUI except in INITIALISED state");
		}	
		this.userInterface = userInterface;
		userInterface.setState(FixBookUI.UI_STATE.READY);
		state = CONTROL_STATE.READY;		
	}
	
	// Review-1.02 Add some more descriptive comments here
    //1.5 Comment Method for scan book
	public void bookScanned(int bookId) {
		if (!state.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("FixBookControl: cannot call bookScanned except in READY state");
		}	
		currentBook = library.Book(bookId);
		
		if (currentBook == null) {
			userInterface.display("Invalid bookId");
			return;
		}
		if (!currentBook.Damaged()) {
			userInterface.display("\"Book has not been damaged");
			return;
		}
		userInterface.display(currentBook.toString());
		userInterface.setState(FixBookUI.UI_STATE.FIXING);
		state = CONTROL_STATE.FIXING;		
	}

	
	//1.6 comment Method for fix book
	// 1.7 Changed boolean Name
	public void fixBook(boolean IsFixed) {
		if (!state.equals(CONTROL_STATE.FIXING)) {
			throw new RuntimeException("FixBookControl: cannot call fixBook except in FIXING state");
		}	
		if (IsFixed) {
			library.repairBook(currentBook);
		}
		currentBook = null;
		userInterface.setState(FixBookUI.UI_STATE.READY);
		state = CONTROL_STATE.READY;		
	}

	// Method to shoe scsnning progress massage
	public void scanningComplete() {
		if (!state.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("FixBookControl: cannot call scanningComplete except in READY state");
		}	
		userInterface.setState(FixBookUI.UI_STATE.COMPLETED);		
	}






}
