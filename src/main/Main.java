package main;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.htmlparser.util.ParserException;

import parsers.VVZStudiesParser;

public class Main {

	public static void main(String[] args) {
		String URL = "http://www.vorlesungen.uzh.ch/HS14/lehrangebot.html";
		String URLPrefix = "www.vorlesungen.uzh.ch/HS14/";
		
		Map<String, List<String>> facultyTitlesMap = null;
		Map<String, List<String>> titlesStudiesMap = null;
		
		Map<String, String> titlesLinksMap = null;
		Map<String, String> studiesLinksMap = null;
		
		try {
			VVZStudiesParser p = new VVZStudiesParser(URL, URLPrefix);
			titlesLinksMap = p.parseStudies();
			facultyTitlesMap = p.parseFaculties();
			
			printMap(titlesLinksMap);
			printMapList(facultyTitlesMap);
			
		} catch (ParserException e) {
			e.printStackTrace();
		}

		try {
			VVZStudiesParser p = new VVZStudiesParser(URL,URLPrefix);

			studiesLinksMap = p.parseStudies();
			titlesStudiesMap = p.parseFaculties();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void printMapList(Map<String, List<String>> map){

		
		String[] array = Arrays.copyOf(map.keySet().toArray(), map.keySet().toArray().length, String[].class);
		
		for(int i=0;i<array.length;i++){
			String key = array[i];
			System.out.println(key);
			System.out.println(map.get(key));
			System.out.println();
		}
	}
	
	public static void printMap(Map<String, String> map){
		String[] array = Arrays.copyOf(map.keySet().toArray(), map.keySet().toArray().length, String[].class);
		
		for(int i=0;i<array.length;i++){
			String key = array[i];
			System.out.println(key);
			System.out.println(map.get(key));
			System.out.println();
		}
	}

}
