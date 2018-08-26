

// Review-1.01 Add author name
/**
 * author : Thilina Gayamal
 */
// Meditater: Mohamed Nashath
import java.util.Scanner; 

public class FixBookUI {
	//Change 1.0 - Added Method Comments
    //UI Status
	public static enum UI_STATE { INITIALISED, READY, FIXING, COMPLETED };

	private FixBookControl control;
	private Scanner input;
	private UI_STATE state;

	//Constructer for Fix book UI
	public FixBookUI(FixBookControl control) {
		this.control = control;
		input = new Scanner(System.in);
		state = UI_STATE.INITIALISED;
		control.setUI(this);
	}

    // Method for set initial state
	public void setState(UI_STATE state) {
		this.state = state;
	}
	
// Review-1.02 Add some more descriptive comments here
//Method for fix book process	

//Run Method
	public void run() {
		output("Fix Book Use Case UI\n");
		
		while (true) {
			
			switch (state) {
			
			case READY:
				String bookStr = input("Scan Book (<enter> completes): ");
				if (bookStr.length() == 0) {
					control.scanningComplete();
				}
				else {
					try {
						int bookId = Integer.valueOf(bookStr).intValue();
						control.bookScanned(bookId);
					}
					catch (NumberFormatException e) {
						output("Invalid bookId");
					}
				}
				break;	
			//Change 1.1 - Change ans to answer	
			case FIXING:
				String answer = input("Fix Book? (Y/N) : ");
				boolean fix = false;
				if (answer.toUpperCase().equals("Y")) {
					fix = true;
				}
				control.fixBook(fix);
				break;
								
			case COMPLETED:
				output("Fixing process complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("FixBookUI : unhandled state :" + state);			
			
			}		
		}
		
	}
// Change 1.2 - Added Private Methgod header comments
	//Input from User
	private String input(String prompt) {
		System.out.print(prompt);
		return input.nextLine();
	}	
		
	//Output based on input provided	
	private void output(Object object) {
		System.out.println(object);
	}
	
//Methos dispaly the output
	public void display(Object object) {
		output(object);
	}
	
	
}
