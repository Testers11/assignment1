
/**
 * author : Malinda Obaysekara
 * Provide the set of functions which related to paying fine
 */


public class PayFineControl {
	
	private PayFineUI userInterface;	// Review 1.2 changed ui to userInterface//change_m_1.01 Variable name has to change 
	private ControlStateEnum state;	//change_m_1.02 Variable name has to change
	private Library library;  //change_m_1.03 Variable name has to change
	private Member member;    //change_m_1.04 Variable name has to change
	
	private PayFineUI userInterface; // Review 1.3 changed ui to userInterface
	private enum CONTROL_STATE { INITIALISED, READY, PAYING, COMPLETED, CANCELLED };
	private CONTROL_STATE state;
	private Library library;  // Review 1.0_ chaned library to Library
	private Member member;;   // Review 1.1_ changed member to Member


	public PayFineControl() {
		this.library = library.INSTANCE();
		state = CONTROL_STATE.INITIALISED;
	}
	
	
	public void setUI(PayFineUI userInterface) {  //Review 1.4_ changed member to Member
		if (!state.equals(CONTROL_STATE.INITIALISED)) {
			throw new RuntimeException("PayFineControl: cannot call setUI except in INITIALISED state");
		}	
		this.userInterface = userInterface;
		userInterface.setState(PayFineUI.UI_STATE.READY); // Review 1.5_ changed member to Member
		state = CONTROL_STATE.READY;		
	}


	public void cardSwiped(int memberId) {
		if (!state.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("PayFineControl: cannot call cardSwiped except in READY state");
		}	
		member = library.getMember(memberId);
		
		if (member == null) {
			userInterface.display("Invalid Member Id");  //Review 1.6_ changed member to Member
			return;
		}
		userInterface.display(member.toString());
	    userInterface.setState(PayFineUI.UI_STATE.PAYING);
		state = CONTROL_STATE.PAYING;
	}
	
	
	public void cancel() {
		userInterface.setState(PayFineUI.UI_STATE.CANCELLED); // Review 1.7_ changed member to Member
		state = CONTROL_STATE.CANCELLED;
	}


	public double payFine(double amount) {
		if (!state.equals(CONTROL_STATE.PAYING)) {
			throw new RuntimeException("PayFineControl: cannot call payFine except in PAYING state");
		}	
		double change = member.payFine(amount);
		if (change > 0) {
			userInterface.display(String.format("Change: $%.2f", change));
		}
		userInterface.display(member.toString());  // Review 1.8_ changed member to Member
		userInterface.setState(PayFineUI.UI_STATE.COMPLETED); // Review 1.9_ changed member to Member
		state = CONTROL_STATE.COMPLETED; 
		return change;
	}
	


}
