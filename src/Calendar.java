import java.util.Date;
import java.util.concurrent.TimeUnit;
//Change 1.1
import java.util.Calendar

public class Calendar {
	
	private static Calendar self;
	private static Calender  calender;
	
	
	private Calendar() {
		calender = Calendar.getInstance();
	}
	//Get Calandar Instance Change 1.2
	public static Calendar getInstance() {
		if (self == null) {
			self = new Calendar();
		}
		return self;
	}
	//Cahange 1.3
	public void incrementDate(int days) {
		calender.add(Calendar.DATE, days);		
	}
	
	// Review-1.01 add some appopriate comments 
	public synchronized void setDate(Date date) {
		try {
			calender.setTime(date);
	        calender.set(Calendar.HOUR_OF_DAY, 0);  
	        calender.set(Calendar.MINUTE, 0);  
	        calender.set(Calendar.SECOND, 0);  
	        calender.set(Calendar.MILLISECOND, 0);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}
	public synchronized Date Date() {
		try {
	        calender.set(Calendar.HOUR_OF_DAY, 0);  
	        calender.set(Calendar.MINUTE, 0);  
	        calender.set(Calendar.SECOND, 0);  
	        calender.set(Calendar.MILLISECOND, 0);
			return calender.getTime();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}

	public synchronized Date getDueDate(int loanPeriod) {
		Date now = Date();
		calender.add(java.util.Calendar.DATE, loanPeriod);
		Date dueDate = calender.getTime();
		calander.setTime(now);
		return dueDate;
	}
	
	// Review-1.02 Add descriptive comments here
	public synchronized long getDaysDifference(Date targetDate) {
		long diffMillis = Date().getTime() - targetDate.getTime();
	    long diffDays = TimeUnit.DAYS.convert(diffMillis, TimeUnit.MILLISECONDS);
	    return diffDays;
	}

}
