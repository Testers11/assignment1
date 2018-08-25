/**
 * change_m_1.01
 * author : Malinda Obaysekara
 * Provide the set of functions which related to paying fine
 */
public class PayFineControl {
	
	
	// change_m_1.02 defining variables, that variables used for initial state of class
	private PayFineUI ui;
	// change_m_1.03enumeration for each state of Control class
	private enum CONTROL_STATE { INITIALISED, READY, PAYING, COMPLETED, CANCELLED };
	private CONTROL_STATE state;	
	private Library library;
	private Member member;;


	// change_m_1.04 setup initial settings for the PayFineControl instance 
	public PayFineControl() {
		this.library = Library.createInstance();
		state = CONTROL_STATE.INITIALISED;
	}
	
	// change_m_1.05 inject PayFineUI Instance to PayFineControl Class
	public void setUI(PayFineUI ui) {
		// change_m_1.05 check for status is in initialized or not
		if (!state.equals(CONTROL_STATE.INITIALISED)) {
			throw new RuntimeException("PayFineControl: cannot call setUI except in INITIALISED state");
		}	
		this.ui = ui;
		ui.setState(PayFineUI.UI_STATE.READY);
		state = CONTROL_STATE.READY;		
	}

	// change_m_1.06 verifying the member by passing member id
	public void cardSwiped(int memberId) {
		// check for status is in READY or not
		if (!state.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("PayFineControl: cannot call cardSwiped except in READY state");
		}	
		member = library.getMember(memberId);
		
		if (member == null) {
			ui.display("Invalid Member Id");
			return;
		}
		ui.display(member.toString());
		ui.setState(PayFineUI.UI_STATE.PAYING);
		state = CONTROL_STATE.PAYING;
	}
	
	// change_m_1.07 handle the status for cancel UI instance and control class 
	public void cancel() {
		ui.setState(PayFineUI.UI_STATE.CANCELLED);
		state = CONTROL_STATE.CANCELLED;
	}

	// change_m_1.08 handle payment by specifying amount & complete the process.
	public double payFine(double amount) {
		if (!state.equals(CONTROL_STATE.PAYING)) {
			throw new RuntimeException("PayFineControl: cannot call payFine except in PAYING state");
		}	
		double change = member.payFine(amount);
		if (change > 0) {
			ui.display(String.format("Change: $%.2f", change));
		}
		ui.display(member.toString());
		ui.setState(PayFineUI.UI_STATE.COMPLETED);
		state = CONTROL_STATE.COMPLETED;
		return change;
	}
	


}
