package pl.coderslab.util;

import java.util.Random;

public class ReservationUtil {
	
	public static String generateNumber() {
		Random r = new Random();
		int first = r.nextInt(10);
		int rest = r.nextInt(100000);
		return String.valueOf(first).concat(String.valueOf(rest));
	}
	
	public static String createSeatDescribed(String string) {
		String[] stringSplit = string.split("-");
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < stringSplit.length; i++) {
			String[] seats = stringSplit[i].split(":");
			String row = seats[0];
			String seat = seats[1];
			sb.append("rząd " + row + " miejsce " + seat);
			sb.append(System.getProperty("line.separator"));
		}
		return sb.toString();
	}
}
