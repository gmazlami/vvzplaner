package io;

public class Utils {

	
	public static String fixUmlauts(String input) {
		String newString = null;
		newString = input.replace("&Auml;", "�");
		newString = newString.replace("&auml;", "�");
		newString = newString.replace("&Uuml;", "�");
		newString = newString.replace("&uuml;", "�");
		newString = newString.replace("&Ouml;", "�");
		newString = newString.replace("&ouml;", "�");
		newString = newString.replace("&nbsp;", " ");
		return newString;
	}
}
