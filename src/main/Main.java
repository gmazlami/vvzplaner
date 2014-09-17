package main;
import io.HTMLDownloader;

import java.io.IOException;
import java.util.Map;

import org.htmlparser.util.ParserException;

import parsers.VVZStudiesParser;

public class Main {

	public static void main(String[] args) {
		String URL = "http://www.vorlesungen.uzh.ch/HS14/lehrangebot.html";
		String URLPrefix = "www.vorlesungen.uzh.ch/HS14/";
		String code = null;
		Map<String, String> linkMap = null;

		try {
			HTMLDownloader d = new HTMLDownloader(URL);

			code = d.getHTML();

		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			VVZStudiesParser p = new VVZStudiesParser(code, URLPrefix);

			linkMap = p.parse();

		} catch (ParserException e) {
			e.printStackTrace();
		}
	}

}
