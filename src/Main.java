import java.text.SimpleDateFormat;
import java.util.Scanner;


public class Main {
	
	private static Scanner inputData;     					// Change cycle 1.1 - change IN into inputData
	private static library library; 						// Change cycle 1.2 - change LIB into library
	private static String menu; 							// Change cycle 1.3 - change MENU into menu
	private static Calendar calendar;						// Change cycle 1.4 - change CAL into calendar
	private static SimpleDateFormat simpleDateFormat;		// Change cycle 1.5 - change SDF into simpleDateFormat
	
	//
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
			library = library.INSTANCE();						// Change cycle 1.11 - change LIB into library
			calendar = Calendar.getInstance();					// Change cycle 1.12 - change CAL into calendar
			simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");		// Change cycle 1.13 - change SDF into simpleDataFormat
	
			for (member m : library.Members()) {				// Change cycle 1.14 - change LIB into library
				output(m);
			}
			output(" ");
			for (book b : library.Books()) {					// Change cycle 1.15 - change LIB into library
				output(b);
			}
						
			menu = getMenu();								//Change cycle 1.16 - change Get_menu into getMenu
			
			boolean isException = false;       				// Change cycle 1.17 - change e into isException
			
			while (!isException) {							// Change cycle 1.18 - change e into isException
				
				output("\n" + simpleDateFormat.format(calendar.Date()));		// Change cycle 1.19 - change SDF into simpleDatEFormat
				String c = input(menu);											// Change cycle 1.20 - change MENU into menu
				
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
		for (loan loan : library.CurrentLoans()) {			// Change cycle 1.25 - change LIB into library
			output(loan + "\n");
		}		
	}



	private static void listBooks() {
		output("");
		for (book book : library.Books()) {
			output(book + "\n");
		}		
	}



	private static void listMembers() {
		output("");
		for (member member : library.Members()) {
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
		book book = library.Add_book(author, title, callNo);
		output("\n" + book + "\n");
		
	}

	
	private static void addMember() {
		try {
			String lastName = input("Enter last name: ");
			String firstName  = input("Enter first name: ");
			String email = input("Enter email: ");
			int phoneNo = Integer.valueOf(input("Enter phone number: ")).intValue();
			member member = library.Add_mem(lastName, firstName, email, phoneNo);
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
