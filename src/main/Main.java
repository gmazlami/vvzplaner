package main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Lecture;

import org.htmlparser.util.ParserException;

import parsers.VVZDetailsParser;
import parsers.VVZFreeSearchParser;
import parsers.VVZLecturesParser;
import parsers.VVZMMParser;
import parsers.VVZMajorMinorParser;
import parsers.VVZMedLecturesParser;
import parsers.VVZStudiesParser;

public class Main {

	private static HashMap<String, HashMap<String, String>> map;
	private static HashMap<String, String> linkMap;

	public static void main(String[] args) {
		String URL = "http://www.vorlesungen.uzh.ch/HS14/lehrangebot.html";
		String URL2 = "http://www.vorlesungen.uzh.ch/HS14/lehrangebot/fak-50000003/sc-50427948/cga-50427948010/cg-50427950.module.html";
		String URL3 = "http://www.vorlesungen.uzh.ch/HS14/lehrangebot/fak-50000003/sc-50332888/cga-50332888010/cg-50332889/sm-50331857.modveranst.html";
		String URLPrefix = "www.vorlesungen.uzh.ch/HS14/";
		String biovorlesung = "http://www.vorlesungen.uzh.ch/HS14/lehrangebot/fak-50000008/sc-50503822/cga-50503822010/cg-50017280/sm-50030313.modveranst.html";
		String infoHauptfach = "http://www.vorlesungen.uzh.ch/HS14/lehrangebot/fak-50000003/sc-50427948.html";
		String sosy = "http://www.vorlesungen.uzh.ch/HS14/lehrangebot/fak-50000003/sc-50427948/cga-50427948010/cg-50427950.module.html";
		String wiwi = "http://www.vorlesungen.uzh.ch/HS14/lehrangebot/fak-50000003/sc-50014157/cga-50014157010/cg-50017343.module.html";

		String majors = "http://www.vorlesungen.uzh.ch/HS14/lehrangebot/fak-50000008/sc-50544113.html";
		String medLectures = "http://www.vorlesungen.uzh.ch/HS14/lehrangebot/fak-50000976/sc-50493524/cga-50493524010/cg-50493525.veranstaltungen.html";
		String medVorlesung = "http://www.vorlesungen.uzh.ch/HS14/lehrangebot/fak-50000976/sc-50493524/cga-50493524010/cg-50493525/e-50693482.details.html";
		Map<String, List<String>> facultyTitlesMap = null;
		Map<String, List<String>> titlesStudiesMap = null;

		String detailsSearchLink = "http://www.vorlesungen.uzh.ch/HS14/suche/sm-50019439.modveranst.html";
		
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
//		try{
//			VVZMMParser parser = new VVZMMParser(majors, URLPrefix);
//			linkMap = parser.parseMajorMinor();
//			System.out.println(linkMap);
//		}catch(ParserException e){
//			e.printStackTrace();
//		}
		
//		try{
//			VVZMajorMinorParser parser  = new VVZMajorMinorParser(majors, URLPrefix);
//			linkMap = parser.parseMajorMinorLinks();
//			System.out.println(linkMap);
//		}catch(ParserException e){
//			e.printStackTrace();
//		}
		
		
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
		
//		VVZMedLecturesParser parser = new VVZMedLecturesParser(medLectures, URLPrefix);
//		try {
//			System.out.println(parser.parseSites());
//		} catch (ParserException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		System.out.println(map);
//		
//		VVZDetailsParser parser = new VVZDetailsParser(detailsSearchLink, URLPrefix);
//		try{
//			Lecture myLecture = parser.parse();
//			
//			System.out.println(myLecture.toString());
//		}catch(ParserException e){
//			e.printStackTrace();
//		}
		
		VVZFreeSearchParser parser = new VVZFreeSearchParser("HS14", "database");
		try{
			parser.getLectureLinks();
		}catch(ParserException e){
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
