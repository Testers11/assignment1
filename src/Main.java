import java.text.SimpleDateFormat;
import java.util.Scanner;


public class Main {
	
	private static Scanner inputData;     	// Change cycle 1.1 - change IN into inputData
	private static Library library; 	// Change cycle 1.2 - change LIB into library       
						// Review_1.1 Object class library (First library after static) should be as Library
						// 1.1 Review done change library class into Library
	private static String menu; 							// Change cycle 1.3 - change MENU into menu
	private static Calendar calendar;						// Change cycle 1.4 - change CAL into calendar
	private static SimpleDateFormat simpleDateFormat;		// Change cycle 1.5 - change SDF into simpleDateFormat
	
	
	private static String getMenu() {   					// Change cycle 1.6 - change Get_menu into getMenu
		StringBuilder stringBuilder = new StringBuilder();	// Change cycle 1.7 - change sb into stringBuilder
		
		stringBuilder.append("\nLibrary Main Menu\n\n")		// Change cycle 1.8 - change sb into stringBuilder
		  .append("  M  : add member\n")
		  .append("  LM : list members\n")
		  .append("\n")
		  .append("  B  : add book\n")
		  .append("  LB : list books\n")
		  .append("  FB : fix books\n")
		  .append("\n")
		  .append("  L  : take out a loan\n")
		  .append("  R  : return a loan\n")
		  .append("  LL : list loans\n")
		  .append("\n")
		  .append("  P  : pay fine\n")
		  .append("\n")
		  .append("  T  : increment date\n")
		  .append("  Q  : quit\n")
		  .append("\n")
		  .append("Choice : ");
		  
		return stringBuilder.toString();					// Change cycle 1.9 - change sb into stringBuilder
	}


	public static void main(String[] args) {		
		try {			
			inputData = new Scanner(System.in);         	// Change cycle 1.10 - change IN into inputData
			library = Library.INSTANCE();			// Change cycle 1.11 - change LIB into library    
									// Review_1.2 library.INSTANCE has to be changed to Library.INSTANCE
									//1.2 Review done change library.INSTANCE into Library.INSTANCE
			calendar = Calendar.getInstance();					// Change cycle 1.12 - change CAL into calendar
			simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");		// Change cycle 1.13 - change SDF into simpleDataFormat
	
			for (Member member : library.Members()) {	// Change cycle 1.14 - change LIB into library    
								// Review_1.3 member is a Class object. It has to be Member and the variable m has to be member.
								//1.3 review done change member into Member and m into member
				output(member);			// Review_1.4 Input parameter variable m has to be member
										// 	1.4 review done change  m into member
			}
			output(" ");
			for (Book book : library.Books()) {	// Change cycle 1.15 - change LIB into library
								// Review_1.5 book is a Class object. It has to be Book and the variable b has to be book.
								//1.5 review done change book into Book and b into book
				output(book);			// Review_1.6 Input parameter variable b has to be book
									//1.6 review done change b into book
			}
						
			menu = getMenu();								//Change cycle 1.16 - change Get_menu into getMenu
			
			boolean isException = false;       				// Change cycle 1.17 - change e into isException
			
			while (!isException) {							// Change cycle 1.18 - change e into isException
				
				output("\n" + simpleDateFormat.format(calendar.Date()));		// Change cycle 1.19 - change SDF into simpleDatEFormat
				String c = input(menu);		// Change cycle 1.20 - change MENU into menu
				
				switch (c.toUpperCase()) {
				
				case "M": 
					addMember();
					break;
					
				case "LM": 
					listMembers();
					break;
					
				case "B": 
					addBook();
					break;
					
				case "LB": 
					listBooks();
					break;
					
				case "FB": 
					fixBooks();
					break;
					
				case "L": 
					borrowBook();
					break;
					
				case "R": 
					returnBook();
					break;
					
				case "LL": 
					listCurrentLoans();
					break;
					
				case "P": 
					payFine();
					break;
					
				case "T": 
					incrementDate();
					break;
					
				case "Q": 
					isException = true;						// Change cycle 1.21 - change e into isException
					break;
					
				default: 
					output("\nInvalid option\n");
					break;
				}
				
				library.SAVE();								// Change cycle 1.22 - change LIB into library
			}			
		} catch (RuntimeException isException) {			// Change cycle 1.23 - change e into isException
			output(isException);							// Change cycle 1.24 - change e into isException
		}		
		output("\nEnded\n");
	}	

		private static void payFine() {
		new PayFineUI(new PayFineControl()).run();		
	}


	private static void listCurrentLoans() {
		output("");
		for (Loan loan : library.CurrentLoans()) {		// Change cycle 1.25 - change LIB into library
									// Review_1.7 loan is a Class object. It has to be Loan.
									// 1.7 review done change class object loan into Loan 
			output(loan + "\n");
		}		
	}



	private static void listBooks() {
		output("");
		for (Book book : library.Books()) {   // Review_1.8 book is a Class object. It has to be Book.
											// 1.8 review done change class object book into Book
			output(book + "\n");
		}		
	}



	private static void listMembers() {
		output("");
		for (Member member : library.Members()) {  // Review_1.9 member is a Class object. It has to be Member.
													// 1.9 review done change class object member into Member
			output(member + "\n");
		}		
	}



	private static void borrowBook() {
		new BorrowBookUI(new BorrowBookControl()).run();		
	}


	private static void returnBook() {
		new ReturnBookUI(new ReturnBookControl()).run();		
	}


	private static void fixBooks() {
		new FixBookUI(new FixBookControl()).run();		
	}


	private static void incrementDate() {
		try {
			int days = Integer.valueOf(input("Enter number of days: ")).intValue();
			calendar.incrementDate(days);
			library.checkCurrentLoans();
			output(simpleDateFormat.format(calendar.Date()));
			
		} catch (NumberFormatException isException) {
			 output("\nInvalid number of days\n");
		}
	}


	private static void addBook() {
		
		String author = input("Enter author: ");
		String title  = input("Enter title: ");
		String callNo = input("Enter call number: ");
		Book book = library.Add_book(author, title, callNo); // Review_1.10 book is a Class object. It has to be Book (First book).
		                                                     //1.10 review done change  class object book into Book
		output("\n" + book + "\n");
		
	}

	
	private static void addMember() {
		try {
			String lastName = input("Enter last name: ");
			String firstName  = input("Enter first name: ");
			String email = input("Enter email: ");
			int phoneNo = Integer.valueOf(input("Enter phone number: ")).intValue();
			Member member = library.Add_mem(lastName, firstName, email, phoneNo);  // Review_1.11 member is a Class object. It has to be Member.
			                                                                    //1.11 review done Change member class object into Member
			output("\n" + member + "\n");
			
		} catch (NumberFormatException isException) {
			 output("\nInvalid phone number\n");
		}
		
	}


	private static String input(String prompt) {
		System.out.print(prompt);
		return inputData.nextLine();
	}
	
	
	
	private static void output(Object object) {
		System.out.println(object);
	}

	
}
