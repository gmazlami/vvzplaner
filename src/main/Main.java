package main;
import io.HTMLDownloader;

import java.io.IOException;
import java.util.Map;

import org.htmlparser.util.ParserException;

import parsers.VVZFacultyParser;
import parsers.VVZMainStudiesParser;
import parsers.VVZStudiesParser;

public class Main {

	public static void main(String[] args) {
		String URL = "http://www.vorlesungen.uzh.ch/HS14/lehrangebot.html";
		String URLPrefix = "www.vorlesungen.uzh.ch/HS14/";
		String code = null;
		Map<String, String> studiesLinksMap = null;

		try {
			HTMLDownloader d = new HTMLDownloader(URL);
			code = d.getHTML();

		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			VVZStudiesParser p = new VVZStudiesParser(code, URLPrefix);

			studiesLinksMap = p.parseStudies();
			p.parseFaculties();
					
		} catch (ParserException e) {
			e.printStackTrace();
		}
		
		
//		try{
//			
//			VVZMainStudiesParser mp = new VVZMainStudiesParser("http://www.vorlesungen.uzh.ch/HS14/lehrangebot/fak-50000007/sc-50306169.html");
//			mp.parse();
//			
//		}catch(ParserException e){
//			e.printStackTrace();
//		}
		
//		try{
//			
//			VVZFacultyParser mp = new VVZFacultyParser("http://www.vorlesungen.uzh.ch/HS14/lehrangebot.html", URLPrefix);
//			mp.parse();
//			
//		}catch(ParserException e){
//			e.printStackTrace();
//		}
	}

}
