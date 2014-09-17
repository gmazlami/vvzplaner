package parsers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class VVZStudiesParser {

	String URLPrefix = null;
	String html = null;

	HashMap<String, String> studiesMap = new HashMap<String, String>();
	HashMap<String, List<String>> facultyMap = new HashMap<String, List<String>>();

	public VVZStudiesParser(String htmlCode, String prefix)
			throws ParserException {
		html = htmlCode;
		URLPrefix = prefix;
	}

	public Map<String, List<String>> parseFaculties() throws ParserException {
		HasAttributeFilter f = new HasAttributeFilter("class", "links");
		Parser parser = new Parser(html);
		NodeList nl = parser.parse(f);
		String faculty = null;

		for (int i = 0; i < nl.size(); i++) {

			Node node = nl.elementAt(i);
			faculty = node.getFirstChild().getFirstChild().getFirstChild()
					.getText().trim();

			NodeList childList = node.getChildren();
			NodeList listChilds = childList.elementAt(1).getChildren();

			List<String> list = new ArrayList<String>();
			for (int j = 0; j < listChilds.size(); j++) {
				list.add(listChilds.elementAt(j).toPlainTextString().trim());
			}
			facultyMap.put(faculty, list);
		}
		System.out.println(facultyMap);
		return facultyMap;
	}

	public Map<String, String> parseStudies() throws ParserException {
		HasAttributeFilter f = new HasAttributeFilter("class", "internal");
		// StringBuilder sb = new StringBuilder();
		Parser parser = new Parser(html);
		NodeList nl = parser.parse(f);
		String str = null;
		String link = null;

		for (int i = 0; i < nl.size(); i++) {

			Node node = nl.elementAt(i);
			str = node.getFirstChild().getText().trim();

			if (!str.contains("Fakul") && !str.contains("&auml;")
					&& !str.contains("&uuml;") && !str.contains("&ouml;")
					&& !str.contains("&Auml") && !str.contains("Weiter")
					&& !str.contains("Double") && !str.contains("Joint")) {

				if (node instanceof Tag) {
					link = ((Tag) node).getAttribute("href");

					studiesMap.put(str, URLPrefix + link);
				}

			}
		}
		return studiesMap;
	}
}
