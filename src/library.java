import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// As the mediator review done
@SuppressWarnings("serial")
public class Library implements Serializable { // change 1.0_ class name should Start with capital

	private static final String LIBRARY_FILE = "library.obj";
	private static final int LOAN_LIMIT = 2;
	private static final int LOAN_PERIOD = 2;
	private static final double FINE_PER_DAY = 1.0;
	private static final double MAX_FINES_OWED = 5.0;
	private static final double DAMAGE_FEE = 2.0;

	private static Library libraryObject; // change 1.2_changed library to Library and changed variable from self to LibraryObject
	private int bookId; //change 1.3_changed variable name from BID to bookId
	private int member; //change 1.4_changed variable name from MID to memberId
	private int loanId; //change 1.5_changed variable name from LID to loanId
	private Date loadDate;

	private Map<Integer, Book> catalog; //change 1.6_ changed object type book to Book
	private Map<Integer, member> members;// Review 1.1 member has to be changed into Member
	private Map<Integer, loan> loans; // Review 1.2 loan shoul be Loan
	private Map<Integer, loan> currentLoans; // Review 1.3 loan should be Loan
	private Map<Integer, Book> damagedBooks; //change 1.7_ change object type book to Book


	private Library() {  // change 1.8_change constructor name from library to Library
		catalog = new HashMap<>();
		members = new HashMap<>();
		loans = new HashMap<>();
		currentLoans = new HashMap<>();
		damagedBooks = new HashMap<>();
		bookId = 1;  //change 1.9_changed BID to bookId
		memberId = 1;  //change 1.10_ changed MID to memberId
		loanId = 1;  //change 1.11_changed LID to loanId
	}


	public static synchronized Library INSTANCE() {   //change 1.12_ changed method name from library to Library    
		if (libraryObject == null) {  //change 1.13_ changed self to libraryObject
			Path path = Paths.get(LIBRARY_FILE);
			if (Files.exists(path)) {
				try (ObjectInputStream libraryObjectFileile = new ObjectInputStream(new FileInputStream(LIBRARY_FILE));) {

					libraryObject = (library) libraryObject.readObject(); //change 1.14_ changed self to libraryObject and lof to libraryObject
					Calendar.getInstance().setDate(libraryObject.loadDate); //change 1.15_changed self to libraryObject
					libraryObjectFile.close(); // change 1.16_changed lof to libraryObjectFile
				}
				catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
			else libraryObject = new library(); //change 1.17_changed self to libraryObject
		}
		return self;
	}


	public static synchronized void SAVE() {
		if (libraryObject != null) { //change 1.18_changed self to libraryObject
			libraryObject.loadDate = Calendar.getInstance().Date(); //change 1.19_changed self to libraryObject
			try (ObjectOutputStream libraryObjectFile = new ObjectOutputStream(new FileOutputStream(LIBRARY_FILE));) {  //change 1.20_changed lof to libraryObjectFile
				libraryObjectFile.writeObject(libraryObject); // change 1.21_changed lof to libraryObjectFile and lof to libraryObject
				libraryObjectFile.flush(); //change 1.22_changed self to libraryObjectFile
				libraryObjectFile.close(); // change 1.23_changed self to libraryObjectFile
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}


	public int getBookId() { //change 1.24_ changed method name from BookID to getBookId
		return bookId; //change 1.25_ change BID to bookId
	}


	public int getMemberID() { //change 1.26_ changed method name from MemberID to getMemberID
		return memberId; //change 1.27_ changed MID to memberId
	}


	private int getnextBID() { //change 1.28_ changed method name from nextBID to getnextBID
		return bookId++; //change 1.29_ changed BID to bookId 
	}


	private int getnextMID() { //change 1.30_ changed method name from nextMIDID to getnextMID
		return memberId++;  //change 1.31_ changed MID to memberId
	}


	private int getnextLID() {   // change 1.32_changed method name from nextBID to getnextBID
		return loanId++;  //change 1.33_ changed LID to loanId
	} 


	public List<member> getMembers() {       //change 1.34_ changed method name from Members to getMembers
		return new ArrayList<member>(members.values());
	}


	public List<Book> getBooks() { //change 1.35_ changed object type from book to Book and changed method name from Books to getBooks
		return new ArrayList<book>(catalog.values());
	}


	public List<loan> getCurrentLoans() {  // change 1.36_changed method name from CurrentLoans to getCurrentLoans
		return new ArrayList<loan>(currentLoans.values());
	}


	public member Add_mem(String lastName, String firstName, String email, int phoneNo) { // Review 1.4 member has to be changed into Member and Add_mem has to be addMember
		member member = new member(lastName, firstName, email, phoneNo, getnextMemberId()); //change 1.37_ changed method from nextMID to getnextMemberId
														//Review 1.5 member (first member) should be Member
		members.put(member.getId(), member);
		return member;
	}


	public Book addBook(String a, String t, String c) { //change 1.38_ changed book to Book  and method name from Add_book to addBook
		Book b = new Book(a, t, c, getnextBookId()); // change 1.39_changed book to Book and changed object from nextBID to getnextBookId
		// Review 1.6 b should be book
		catalog.put(book.ID(), book); //change 1.40_changed b to book
		return book;  //change 1.41_changed b to book

	}


	public member getMember(int memberId) {		//Review 1.7 member should be Member
		if (members.containsKey(memberId))
			return members.get(memberId);
		return null;
	}


	public Book getBook(int bookId) { // change 1.42_changed return type from book to Book, changed method name from Book to getBook
		if (catalog.containsKey(bookId))
			return catalog.get(bookId);
		return null;
	}


	public int getloanLimit() {  //change 1.43_ changed loanLimit to getloanlimit
		return LOAN_LIMIT;
	}


	public boolean memberCanBorrow(member member) {
		if (member.getNumberOfCurrentLoans() == LOAN_LIMIT )
			return false;

		if (member.getFinesOwed() >= MAX_FINES_OWED)
			return false;

		for (loan loan : member.getLoans()) // Review 1.8 loan (first loan) should be Loan
			if (loan.isOverDue())
				return false;

		return true;
	}


	public int loansRemainingForMember(member member) {  //Review 1.9 member (first member) should be Member
		return LOAN_LIMIT - member.getNumberOfCurrentLoans();
	}


	public loan issueLoan(Book book, member member) { //change 1.44_ changed parameter object type from book to Book
														//Review 1.10 member (first member) should be Member
														//Review 1.11 loan  should be Loan
		Date dueDate = Calendar.getInstance().getDueDate(LOAN_PERIOD);
		loan loan = new loan(getNextLoanId(), book, member, dueDate); //change 1.45_ change method from nextID to getNextLoanId
																		//Review 1.12 loan (first loan) should be Loan
		member.takeOutLoan(loan);
		book.Borrow();
		loans.put(loan.getId(), loan);
		currentLoans.put(book.ID(), loan);
		return loan;
	}


	public loan getLoanByBookId(int bookId) {			//Review 1.13 loan (first loan) should be Loan
		if (currentLoans.containsKey(bookId)) {
			return currentLoans.get(bookId);
		}
		return null;
	}


	public double calculateOverDueFine(loan loan) {		//Review 1.14 loan (first loan) should be Loan
		if (loan.isOverDue()) {
			long daysOverDue = Calendar.getInstance().getDaysDifference(loan.getDueDate());
			double fine = daysOverDue * FINE_PER_DAY;
			return fine;
		}
		return 0.0;
	}


	public void dischargeLoan(loan currentLoan, boolean isDamaged) {
		member member = currentLoan.Member();					//Review 1.15 member (first member) should be Member
		Book book  = currentLoan.Book(); // changed object type fromm book to Book

		double overDueFine = calculateOverDueFine(currentLoan);
		member.addFine(overDueFine);

		member.dischargeLoan(currentLoan);
		book.Return(isDamaged);			//Review 1.16 Return has to be changed into returnBook (please refer book.java class)
		if (isDamaged) {
			member.addFine(DAMAGE_FEE);
			damagedBooks.put(book.ID(), book);
		}
		currentLoan.Loan();
		currentLoans.remove(book.ID());
	}


	public void checkCurrentLoans() {
		for (loan loan : currentLoans.values()) {				//Review 1.17 loan (first loan) should be Loan
			loan.checkOverDue();
		}
	}


	public void repairBook(Book currentBook) {  //change 1.46_ change object type from book to Book
		if (damagedBooks.containsKey(currentBook.ID())) {
			currentBook.Repair();				//Review 1.18 Repair has to be changed into repairBook (please refer book.java class)
			damagedBooks.remove(currentBook.ID());
		}
		else {
			throw new RuntimeException("Library: repairBook: book is not damaged");
		}

	}


}
