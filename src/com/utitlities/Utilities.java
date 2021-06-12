package com.utitlities;

import static java.lang.System.err;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author sushi
 */
public class Utilities {

	private static final Calendar calendar = Calendar.getInstance();
	private static final Map<Integer, String> months;
	private static final Map<String, Integer> monthsByNumber;
	private static final Map<Integer, String> daysOfWeek;
	private static Timestamp timestamp;
	private static final Date date;
	private static SimpleDateFormat dateFormat;
	private static LocalDate today;
	private static BufferedReader bufferedReader;
	private static InputStreamReader inputStreamReader;
	private static ObservableList<String> yearsObservableList = FXCollections.observableArrayList();
	private static final Map<Integer, Integer> monthAndDays;
	private static String OS = System.getProperty("os.name").toLowerCase();

	static {
		months = new HashMap<>();
		monthsByNumber = new HashMap<>();
		monthAndDays = new HashMap<>();
		daysOfWeek = new HashMap<>();
		long mills = System.currentTimeMillis();
		date = new Date(mills);
		months.put(0, "January");
		months.put(1, "February");
		months.put(2, "March");
		months.put(3, "April");
		months.put(4, "May");
		months.put(5, "Jun");
		months.put(6, "July");
		months.put(7, "August");
		months.put(8, "September");
		months.put(9, "October");
		months.put(10, "November");
		months.put(11, "December");

		monthsByNumber.put("January", 1);
		monthsByNumber.put("February", 2);
		monthsByNumber.put("March", 3);
		monthsByNumber.put("April", 4);
		monthsByNumber.put("May", 5);
		monthsByNumber.put("Jun", 6);
		monthsByNumber.put("July", 7);
		monthsByNumber.put("August", 8);
		monthsByNumber.put("September", 9);
		monthsByNumber.put("October", 10);
		monthsByNumber.put("November", 11);
		monthsByNumber.put("December", 12);

		daysOfWeek.put(1, "Sunday");
		daysOfWeek.put(2, "Monday");
		daysOfWeek.put(3, "Tuesday");
		daysOfWeek.put(4, "Wednsday");
		daysOfWeek.put(5, "Thursday");
		daysOfWeek.put(6, "Friday");
		daysOfWeek.put(7, "Saturday");

		for (long year = 1999; year <= 2100; year++) {
			yearsObservableList.add(String.valueOf(year));
		}

	}

	private boolean aBoolean;

	// This method loads properties and returns instance of the properties class

	/**
	 * @param file
	 * @return properties instanc
	 */
	public static Properties loadProperties(File file) {
		Properties properties = null;
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			properties = new Properties();
			properties.load(fileInputStream);
		} catch (IOException e) {
			System.out.println("Caught an exception " + e.getMessage());
			e.printStackTrace();
		}
		return properties;
	}

	/**
	 * @return current day
	 */
	public static String getCurrentDay() {
		return daysOfWeek.get(calendar.get(Calendar.DAY_OF_WEEK));
	}

	/**
	 * @return date for sunday of the current week
	 */
	private static LocalDate getSunday() {
		today = LocalDate.now();
		// Go forward to get Sunday
		LocalDate sunday = today;
		while (sunday.getDayOfWeek() != DayOfWeek.SUNDAY) {
			sunday = sunday.plusDays(1);
		}
		return sunday;
	}

	/**
	 * @return date for monday of the current week
	 */
	private static LocalDate getMonday() {
		today = LocalDate.now();
		// Go backward to get Monday
		LocalDate monday = today;
		while (monday.getDayOfWeek() != DayOfWeek.MONDAY) {
			monday = monday.minusDays(1);
		}
		return monday;
	}

	/**
	 * @return first day date of current week
	 */
	@Deprecated
	public static LocalDate getCurrentWeekStartDate() {
		LocalDate startDate = null;
		try {
			startDate = Utilities.getMonday();
			System.out.println(getMonday().compareTo(getSunday()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return startDate;
	}

	/**
	 * @return last day date of current week
	 */
	@Deprecated
	public static LocalDate getCurrentWeekEndDate() {
		LocalDate startDate = null;
		try {
			startDate = Utilities.getSunday();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return startDate;
	}

	/**
	 * @return current month name
	 */
	public static String getCurrentMonthByName() {
		return months.get(calendar.get(Calendar.MONTH));
	}

	/**
	 * @return current month number in string format
	 */
	public static String getCurrentMonth() {
		return String.valueOf(calendar.get(Calendar.MONTH) + 1);
	}

	/**
	 * @return current year
	 */
	public static String getCurrentYear() {
		return String.valueOf(calendar.get(Calendar.YEAR));
	}

	/**
	 * @return current date(today's)
	 */
	public static Date getCurrentDate() {
		return date;
	}

	/**
	 * @return current time
	 */
	public static Timestamp getTimeNow() {
		Calendar calendar = Calendar.getInstance();
		Timestamp timestamp = new Timestamp(calendar.getTime().getTime());
		return timestamp;
	}

	/**
	 * @param date
	 * @return formatted date string
	 */
	public static String formate(Date date) {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(date);
	}

	/**
	 * @param timestamp
	 * @return formatted time string
	 */
	public static String formate(Timestamp timestamp) {
		dateFormat = new SimpleDateFormat("hh:mm:ss a");
		return dateFormat.format(timestamp);
	}

	/**
	 * @param start   milli seconds
	 * @param strUnit in minutes,seconds,hours or days
	 */
	@Deprecated
	public static void showTimeTaken(long start, String strUnit) {
		String unit = strUnit.toLowerCase();
		switch (unit) {
		case "seconds":
			String strSeconds = String.valueOf(Duration.ofMillis(System.currentTimeMillis() - start)).substring(2);
			err.println("Time Taken:" + strSeconds);
			break;
		case "minutes":
			String strMinutes = String.valueOf(Duration.ofMillis(System.currentTimeMillis() - start).toMinutes())
					.substring(2);
			err.println("Time Taken:" + strMinutes + " Minutes");
			break;
		case "hours":
			String strHours = String.valueOf(Duration.ofMillis(System.currentTimeMillis() - start).toHours())
					.substring(2);
			err.println("Time Taken:" + strHours + " Hours");
			break;
		case "days":
			String strDays = String.valueOf(Duration.ofMillis(System.currentTimeMillis() - start).toDays())
					.substring(2);
			err.println("Time Taken:" + strDays + " Days");
			break;
		default:
			break;
		}
	}

	/**
	 * @return true if laptop /pc is connected to internet otherwise false
	 */
	public static boolean isConnectedToInternet() {
		boolean blnFlag = false;
		try {
			Process process = Runtime.getRuntime().exec("ping www.google.com");
			InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream());
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String strResponse = bufferedReader.readLine();
			if (strResponse.contains("Unknown host") || strResponse.contains("could not find host")) {
				blnFlag = false;
			} else {
				blnFlag = true;
			}
		} catch (IOException e) {
			System.out.println("Caught An Exception " + e.getMessage());
		}
		return blnFlag;
	}

	/**
	 * @param password
	 * @param salt
	 * @param iterations
	 * @param keyLength
	 * @return byte array for password
	 */
	public static byte[] hashPassword(final char[] password, final byte[] salt, final int iterations,
			final int keyLength) {
		try {
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
			PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keyLength);
			SecretKey key = skf.generateSecret(spec);
			byte[] res = key.getEncoded();
			return res;
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @param date
	 * @return age for given birth date
	 */
	public static int getAge(LocalDate date) {
		int intAge = 0;
		try {
			LocalDate now = LocalDate.now();
			intAge = now.compareTo(date);
		} catch (Exception e) {
			System.out.println("Caught An Exception " + e.getMessage());
		}
		return intAge;
	}

	/**
	 * @param month
	 * @return month number from given month name
	 */
	public static int getMonth(String month) {
		int intMonth = 0;
		for (Map.Entry<Integer, String> iter : months.entrySet()) {
			if (iter.getValue().equalsIgnoreCase(month)) {
				intMonth = iter.getKey();
				break;
			}
		}
		return intMonth + 1;
	}

	/**
	 * @return
	 */
	public static String getToday() {
		String strDate = "";
		try {
			strDate = String.valueOf(calendar.get(Calendar.DATE));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strDate;
	}

	public static LocalDate getFirstDayDateOfMonth() {
		int date = Calendar.getInstance().getActualMinimum(Calendar.DATE);
		LocalDate todaydate = LocalDate.now();
		System.out.println("******First Date->" + todaydate.withDayOfMonth(date));
		return todaydate.withDayOfMonth(date);
	}

	public static LocalDate getLastDayDateOfMonth() {
		int date = Calendar.getInstance().getActualMaximum(Calendar.DATE);
		LocalDate todaydate = LocalDate.now();
		System.out.println("******Last Date->" + todaydate.withDayOfMonth(date));
		return todaydate.withDayOfMonth(date);
	}

	private static void addMonthDays(String month, String year) {
		int yearNum = Integer.parseInt(year);
		for (int i = 1; i <= monthsByNumber.size(); i++) {
			if (i == 1) {
				monthAndDays.put(i, 31);
			}
			if (i == 2) {
				if (isLeapYear(yearNum)) {
					monthAndDays.put(i, 29);
				} else {
					monthAndDays.put(i, 28);
				}
			}
			if (i == 8) {
				monthAndDays.put(i, 31);
			} else if (i != 1 && i != 2 && i != 8) {
				if (i > 8) {
					if (i % 2 == 0) {
						monthAndDays.put(i, 31);
					} else {
						monthAndDays.put(i, 30);
					}
				} else {
					if (i % 2 == 0) {
						monthAndDays.put(i, 30);
					} else {
						monthAndDays.put(i, 31);
					}
				}
			}
		}
	}

	public static LocalDate getFirstDayOfMonth(String month, String year) {
		addMonthDays(month, year);
		int yearNum = Integer.parseInt(year);
		int monthNum = monthsByNumber.get(month);
		LocalDate localDate = LocalDate.of(yearNum, monthNum, 1);
		System.out.println(month + " " + localDate);
		return (localDate);
	}

	public static LocalDate getLastDayOfMonth(String month, String year) {
		if (monthAndDays.size() == 0) {
			addMonthDays(month, year);
		}
		int yearNum = Integer.parseInt(year);
		int monthNum = monthsByNumber.get(month);
		int day = monthAndDays.get(monthNum);
		LocalDate localDate = LocalDate.of(yearNum, monthNum, day);
		System.out.println(month + " " + localDate);
		return (localDate);
	}

	public static ObservableList<String> getMonthsByName() {
		ObservableList monthsObservableList = FXCollections.observableArrayList(months.values());
		return monthsObservableList;
	}

	public static ObservableList<String> getYears() {
		return yearsObservableList;
	}

	public static boolean openFile(File file, String type) {
		boolean status = false;
		if (Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().open(file);
				status = true;
			} catch (IOException e) {
				e.printStackTrace();
				status = false;
			}
			System.out.println("OPEN FILE OK? - >" + status);
		} else {
			System.out.println("Not Supported");
		}

		return status;
	}

	public static boolean isLeapYear(int year) {
		boolean leap;
		if (year % 4 == 0) {
			if (year % 100 == 0) {
				// year is divisible by 400, hence the year is a leap year
				if (year % 400 == 0)
					leap = true;
				else
					leap = false;
			} else
				leap = true;
		} else
			leap = false;
		return leap;
	}

	public static boolean isWindows() {

		return (OS.indexOf("win") >= 0);

	}

	public static boolean isMac() {

		return (OS.indexOf("mac") >= 0);

	}

	public static boolean isUnix() {

		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);

	}

	public static boolean isSolaris() {

		return (OS.indexOf("sunos") >= 0);

	}

	public static String convert12(String input) {
		// Format of the date defined in the input String
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// Desired format: 24 hour format: Change the pattern as per the need
		DateFormat outputformat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");
		java.util.Date date = null;
		String output = null;
		try {
			// Converting the input String to Date
			date = df.parse(input);
			// Changing the format of date and storing it in String
			output = outputformat.format(date);
			// Displaying the date
			System.out.println(output);
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
		return output;
	}

	public static void main(String[] args) {
		for (int i = 1; i <= monthsByNumber.size(); i++) {
			getFirstDayOfMonth(months.get(i - 1), "2003");
			getLastDayOfMonth(months.get(i - 1), "2003");
			System.out.println("************************");
		}
	}
}
