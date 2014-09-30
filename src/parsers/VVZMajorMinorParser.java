package parsers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class VVZMajorMinorParser {

	String URLPrefix = null;
	String URL = null;

	HashMap<String, String> studiesMap = new HashMap<String, String>();
	HashMap<String, List<String>> facultyMap = new HashMap<String, List<String>>();

	public VVZMajorMinorParser(String url, String prefix)
			throws ParserException {
		URL = url;
		URLPrefix = prefix;
	}

	
	public List<String> parseMajors() throws ParserException{
		return extractMajors(parseMajorMinor());
	}
	
	public HashMap<String, List<String>> parseMajorsStudies() throws ParserException{
		return extractMajorsStudiesMap(parseMajorMinor());
	}
	
	
	public HashMap<String, String> parseMajorMinorLinks()
			throws ParserException {
		HasAttributeFilter filter = new HasAttributeFilter("class",
				"linked noimage");
		Parser parser = new Parser();
		parser.setResource(URL);

		HashMap<String, HashMap<String, String>> majorMinorMap = new HashMap<String, HashMap<String, String>>();
		HashMap<String, String> linksMap = new HashMap<String, String>();
		NodeList nl = parser.parse(filter);
		for (int i = 0; i < nl.size(); i++) {

			Node rootMajorMinor = nl.elementAt(i);
			Node majorMinor = rootMajorMinor.getFirstChild();
			Node firstStudy = rootMajorMinor.getNextSibling().getFirstChild();

			String majorMinorString = majorMinor.toPlainTextString().trim();

			Node current = firstStudy;

			while (current != null) {

				String currentString = current.toPlainTextString().trim();
				if (!currentString.contains("Dieses Studienprogramm")
						&& !currentString.contains("vom neuen")) {
					String str = current.toHtml().trim();
					String[] first = str.split("A href=\"");
					String[] scnd = first[1].split("\" class=\"internal\">");
					String link = scnd[0];
					if (link.contains("../../")) {
						link = link.replace("../../", "");
						String postFix ="";
						if(majorMinorString.contains("Hauptfach")){
							postFix = majorMinorString.replace("Hauptfach", "HF");
						}else if(majorMinorString.contains("Nebenfach")){
							postFix = majorMinorString.replace("Nebenfach", "NF");
						}
						linksMap.put(currentString+" "+postFix, URLPrefix + link);
					}
				}
				current = current.getNextSibling();
			}
			majorMinorMap.put(majorMinorString, linksMap);
		}
		return linksMap;
	}
	
	private List<String> extractMajors(
			HashMap<String, HashMap<String, String>> map) {
		String[] array = new String[map.keySet().size()];
		array = map.keySet().toArray(array);
		return Arrays.asList(array);
	}

	private HashMap<String, List<String>> extractMajorsStudiesMap(
			HashMap<String, HashMap<String, String>> map) {
		HashMap<String, List<String>> result = new HashMap<String, List<String>>();
		for (String s : map.keySet()) {
			Map<String, String> subMap = map.get(s);
			ArrayList<String> list = new ArrayList<String>();
			list.addAll(subMap.keySet());
			result.put(s, list);
		}
		return result;
	}
	

	
	
	private HashMap<String, HashMap<String, String>> parseMajorMinor()
			throws ParserException {
		HasAttributeFilter filter = new HasAttributeFilter("class",
				"linked noimage");
		Parser parser = new Parser();
		parser.setResource(URL);

		HashMap<String, HashMap<String, String>> majorMinorMap = new HashMap<String, HashMap<String, String>>();
		NodeList nl = parser.parse(filter);
		for (int i = 0; i < nl.size(); i++) {

			Node rootMajorMinor = nl.elementAt(i);
			Node majorMinor = rootMajorMinor.getFirstChild();
			Node firstStudy = rootMajorMinor.getNextSibling().getFirstChild();

			String majorMinorString = majorMinor.toPlainTextString().trim();

			Node current = firstStudy;

			HashMap<String, String> linksMap = new HashMap<String, String>();
			while (current != null) {

				String currentString = current.toPlainTextString().trim();
				if (!currentString.contains("Dieses Studienprogramm")
						&& !currentString.contains("vom neuen")) {
					String str = current.toHtml().trim();
					String[] first = str.split("A href=\"");
					String[] scnd = first[1].split("\" class=\"internal\">");
					String link = scnd[0];
					if (link.contains("../../")) {
						link = link.replace("../../", "");
						linksMap.put(currentString, URLPrefix + link);
					}
				}
				current = current.getNextSibling();
			}
			majorMinorMap.put(majorMinorString, linksMap);
		}
		return majorMinorMap;
	}
}
