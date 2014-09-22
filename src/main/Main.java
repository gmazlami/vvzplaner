package main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Lecture;

import org.htmlparser.util.ParserException;

import parsers.VVZDetailsParser;
import parsers.VVZLecturesParser;
import parsers.VVZMajorMinorParser;
import parsers.VVZStudiesParser;

public class Main {

	private static HashMap<String, HashMap<String, String>> map;

	public static void main(String[] args) {
		String URL = "http://www.vorlesungen.uzh.ch/HS14/lehrangebot.html";
		String URL2 = "http://www.vorlesungen.uzh.ch/HS14/lehrangebot/fak-50000003/sc-50427948/cga-50427948010/cg-50427950.module.html";
		String URL3 = "http://www.vorlesungen.uzh.ch/HS14/lehrangebot/fak-50000003/sc-50332888/cga-50332888010/cg-50332889/sm-50331857.modveranst.html";
		String URLPrefix = "www.vorlesungen.uzh.ch/HS14/";
		String biovorlesung = "http://www.vorlesungen.uzh.ch/HS14/lehrangebot/fak-50000008/sc-50503822/cga-50503822010/cg-50017280/sm-50030313.modveranst.html";
		String infoHauptfach = "http://www.vorlesungen.uzh.ch/HS14/lehrangebot/fak-50000003/sc-50427948.html";
		String sosy = "http://www.vorlesungen.uzh.ch/HS14/lehrangebot/fak-50000003/sc-50427948/cga-50427948010/cg-50427950.module.html";
		String wiwi = "http://www.vorlesungen.uzh.ch/HS14/lehrangebot/fak-50000003/sc-50014157/cga-50014157010/cg-50017343.module.html";

		String majors = "http://www.vorlesungen.uzh.ch/HS14/lehrangebot/fak-50000003/sc-50427948.html";
		
		
		Map<String, List<String>> facultyTitlesMap = null;
		Map<String, List<String>> titlesStudiesMap = null;

		Map<String, String> titlesLinksMap = null;
		Map<String, String> studiesLinksMap = null;
//		
//		try {
//			VVZStudiesParser p = new VVZStudiesParser(URL, URLPrefix);
//			titlesLinksMap = p.parseStudies();
//			facultyTitlesMap = p.parseFaculties();
//			
//			printMap(titlesLinksMap);
////			printMapList(facultyTitlesMap);
//			
//		} catch (ParserException e) {
//			e.printStackTrace();
//		}
//
		
		
		try{
			VVZMajorMinorParser parser  = new VVZMajorMinorParser(majors, URLPrefix);
			map = parser.parseMajorMinor();
			System.out.println(map);
		}catch(ParserException e){
			e.printStackTrace();
		}
		
		
//		try {
//			VVZStudiesParser p = new VVZStudiesParser(majors,URLPrefix);
//
//			studiesLinksMap = p.parseStudies();
////			printMap(studiesLinksMap);
//			titlesStudiesMap = p.parseFaculties();
//			printMapList(titlesStudiesMap);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
//		VVZLecturesParser parser = new VVZLecturesParser(arab, URLPrefix);
//		Map<String, String> map = null;
//		try {
//			System.out.println(parser.parse());
//		} catch (ParserException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		System.out.println(map);
//		
//		VVZDetailsParser parser = new VVZDetailsParser(biovorlesung, URLPrefix);
//		try{
//			Lecture myLecture = parser.parse();
//			
//			System.out.println(myLecture.toString());
//		}catch(ParserException e){
//			e.printStackTrace();
//		}

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
