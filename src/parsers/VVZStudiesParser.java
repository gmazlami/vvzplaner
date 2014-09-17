package parsers;
import java.util.HashMap;
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
	HashMap<String, String> facultyMap = new HashMap<String, String>();

	public VVZStudiesParser(String htmlCode, String prefix) throws ParserException {
		html = htmlCode;
		URLPrefix = prefix;
	}

	public Map<String, String> parseFaculties() throws ParserException {
		HasAttributeFilter f = new HasAttributeFilter("class", "links");
		Parser parser = new Parser(html);
		NodeList nl = parser.parse(f);
		String faculty = null;

		for (int i = 0; i < nl.size(); i++) {

			Node node = nl.elementAt(i);
			faculty = node.getFirstChild().getFirstChild().getFirstChild().getText().trim();

			System.out.println("Current Faculty:");
			System.out.println(faculty);
			System.out.println("----------------");
			System.out.println();
			NodeList childList = node.getChildren();
			NodeList listChilds = childList.elementAt(1).getChildren();
			
			for(int j = 0 ; j < listChilds.size(); j++){
				System.out.println(listChilds.elementAt(j).toPlainTextString().trim());
			}
			
			System.out.println();
			
//			System.out.println(faculty);


		}
		return studiesMap;
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
				// sb.append(node.getChildren().elementAt(0).getText().trim());
				// sb.append("\n");

				if (node instanceof Tag) {
					link = ((Tag) node).getAttribute("href");
					// System.out.println(((Tag)node).getAttribute("href"));

					studiesMap.put(str, URLPrefix + link);
				}

			}
		}
		// return sb.toString();
		// System.out.println(map);
		return studiesMap;
	}
}
