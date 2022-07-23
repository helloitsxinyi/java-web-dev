package sg.edu.iss.caps.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {
	
	//Handling LocalDateTime
	private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E MMM d HH:mm:ss (Z) yyyy");
	private static DateTimeFormatter dtf_local = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");
	
	//Handling Date
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
	
	
	public static String GetCurrentZoneTime() {
		//Obtain current ZonedDateTime and convert to string
		ZonedDateTime now = ZonedDateTime.now();
		return now.format(dtf).toString();
	}
	
	public static String ConvertFromLocalDateTime(LocalDateTime date) {
		//Convert LocalDateTime to String
		return date.format(dtf_local).toString();
	}
	
	public static String ConvertFromDate(Date date) {
		//Convert Date to String
		return sdf.format(date);
	}
	

}
