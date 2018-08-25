
import java.util.Scanner;

/**
 * change_m_1.01
 * author : Malinda Obaysekara 
 * provide the set of functions related to user
 * interface : Paying fine functions
 * 
 */
public class PayFineUI {

	// change_m_1.02 enumeration for each state of UI
	public  enum UI_STATE {
		INITIALISED, READY, PAYING, COMPLETED, CANCELLED
	};

	private PayFineControl control;
	private Scanner input;
	private UI_STATE state;

	// change_m_1.03 setup configuration for initializing PayFineUi
	public PayFineUI(PayFineControl control) {
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
	 * change_m_1.05 Provide provision for process of fine payments
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
				} catch (NumberFormatException e) {
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
				} catch (NumberFormatException e) {
				}
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

	// change_m_1.06 Manage user's input to the Payment handling UI
	private String input(String prompt) {
		System.out.print(prompt);
		return input.nextLine();
	}

	// change_m_1.07 Print the passed object , that can be shown in UI
	private void output(Object object) {
		System.out.println(object);
	}

	// change_m_1.08 Manage information to show in user interface.
	public void display(Object object) {
		output(object);
	}

}
