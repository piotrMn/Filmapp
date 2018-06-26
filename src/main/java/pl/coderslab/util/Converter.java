package pl.coderslab.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Converter {

	public static String reverseDate(String date) {
		String regex = "\\d{2}-\\d{2}-\\d{4}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(date);
		if (!matcher.matches()) {
			return null;
		}
		String year = date.substring(date.length() - 4);
		String day = date.substring(0, 2);
		String month = date.substring(3, 5);
		return year + "-" + month + "-" + day;
	}
	
	public static List<String> convertSeatCodes(String bookedSeatsInString, int rows, int seatsInRow){
		Map<Integer, String> seatMap = new HashMap<>();
		for(Integer i = 1; i <= rows; i++) {
			StringBuilder sb = new StringBuilder();
			for(int j = 0; j < seatsInRow; j++) {
				sb.append(String.valueOf('0'));
			}
			seatMap.put(i, sb.toString());
		}
		if(bookedSeatsInString != null && !bookedSeatsInString.equals("")) {
			String[] seatCodes = bookedSeatsInString.split("-");
			for(String seatcode : seatCodes) {
				String[] rowseat = seatcode.split(":");
				Integer row = Integer.parseInt(rowseat[0]);
				Integer seat = Integer.parseInt(rowseat[1]);
				String rowString = seatMap.get(row);
				char[] chars = rowString.toCharArray();
				chars[seat - 1] = '1';
				seatMap.put(row,new String(chars));
			}
		} 
		List<String> resultList = new ArrayList<>();
		for(Integer i = 1; i <= seatMap.keySet().size(); i++) {
			resultList.add(seatMap.get(i));
		}
		return resultList;
	}
	
}
