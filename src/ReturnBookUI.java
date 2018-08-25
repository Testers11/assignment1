import java.util.Scanner;

/**
 * change_m_1.01
 * author : Malinda Obaysekara 
 * provide the set of functions related to user
 * interface : Return Book UI functions
 *
 */
public class ReturnBookUI {

	// change_m_1.02 enumeration for each state of UI
	public static enum UI_STATE {
		INITIALISED, READY, INSPECTING, COMPLETED
	};

	private ReturnBookControl control;
	private Scanner input;
	public UI_STATE state;

	// change_m_1.03 setup configuration for initializing ReturnBookUI
	public ReturnBookUI(ReturnBookControl control) {
		this.control = control;
		input = new Scanner(System.in);
		state = UI_STATE.INITIALISED;
		control.setUI(this);
	}
	
	
	// change_m_1.04 setup UI status to control class
		public void setState(UI_STATE state) {
			this.state = state;
		}

	/**
	 * change_m_1.05 Provide provision for process of returning books
	 */
	public void run() {
		output("Return Book Use Case UI\n");

		while (true) {

			switch (state) {

			case INITIALISED:
				break;

			case READY:
				String bookStr = input("Scan Book (<enter> completes): ");
				if (bookStr.length() == 0) {
					control.scanningComplete();
				} else {
					try {
						int bookId = Integer.valueOf(bookStr).intValue();
						control.bookScanned(bookId);
					} catch (NumberFormatException e) {
						output("Invalid bookId");
					}
				}
				break;

			case INSPECTING:
				String ans = input("Is book damaged? (Y/N): ");
				boolean isDamaged = false;
				if (ans.toUpperCase().equals("Y")) {
					isDamaged = true;
				}
				control.dischargeLoan(isDamaged);

			case COMPLETED:
				output("Return processing complete");
				return;

			default:
				output("Unhandled state");
				throw new RuntimeException("ReturnBookUI : unhandled state :" + state);
			}
		}
	}

	
	private String input(String prompt) {
		System.out.print(prompt);
		return input.nextLine();
	}

	
	private void output(Object object) {
		System.out.println(object);
	}

	
	public void display(Object object) {
		output(object);
	}

	

}
