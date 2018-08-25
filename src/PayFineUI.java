

import java.util.Scanner;

/**
 * m_1.01 changes
 * author : Malinda Obaysekara 
 * provide the set of functions related to user interface
 *
 */


public class PayFineUI {
	// Hashini test.....

	public static enum UI_STATE { INITIALISED, READY, PAYING, COMPLETED, CANCELLED };
	
	// m_1.02 changes Define variable that need to startup 
	private PayFineControl control;
	private Scanner input;
	private UI_STATE state;

	// m_1.03 changes define constructor 
	public PayFineUI(PayFineControl control) {
		this.control = control;
		input = new Scanner(System.in);
		state = UI_STATE.INITIALISED;
		control.setUI(this);
	}
	
	
	public void setState(UI_STATE state) {
		this.state = state;
	}

	/**
	 * m_1.04 changes
	 * Provide provision for process of fine payments
	 */
	public void run() {
		output("Pay Fine Use Case UI\n");
		
		while (true) {
			
			switch (state) {
			
			case READY:
				String memStr = input("Swipe member card (press <enter> to cancel): ");
				if (memStr.length() == 0) {
					control.cancel();
					break;
				}
				try {
					int memberId = Integer.valueOf(memStr).intValue();
					control.cardSwiped(memberId);
				}
				catch (NumberFormatException e) {
					output("Invalid memberId");
				}
				break;
				
			case PAYING:
				double amount = 0;
				String amtStr = input("Enter amount (<Enter> cancels) : ");
				if (amtStr.length() == 0) {
					control.cancel();
					break;
				}
				try {
					amount = Double.valueOf(amtStr).doubleValue();
				}
				catch (NumberFormatException e) {}
				if (amount <= 0) {
					output("Amount must be positive");
					break;
				}
				control.payFine(amount);
				break;
								
			case CANCELLED:
				output("Pay Fine process cancelled");
				return;
			
			case COMPLETED:
				output("Pay Fine process complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("FixBookUI : unhandled state :" + state);			
			
			}		
		}		
	}

	// m_1.05 changes Manage  user's input to the Payment handling UI
	private String input(String prompt) {
		System.out.print(prompt);
		return input.nextLine();
	}	
		
	// m_1.06 changes Manage to show user's input to UI 	
	private void output(Object object) {
		System.out.println(object);
	}	
			
	// m_1.07 changes Manage information to show in user interface
	public void display(Object object) {
		output(object);
	}


}
