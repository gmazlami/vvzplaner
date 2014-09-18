package parsers;

import java.util.HashMap;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class VVZLecturesParser {

	private String resourceURL = null;
	private String URLprefix = null;
	private HashMap<String, String> linkMap = new HashMap<String, String>();

	public VVZLecturesParser(String url, String prefix) {
		this.resourceURL = url;
		this.URLprefix = prefix;
	}

	public void parse() throws ParserException {
		Parser parser = new Parser();
		parser.setResource(resourceURL);
		HasAttributeFilter f = new HasAttributeFilter("class", "internal");
		NodeList nl = parser.parse(f);

		String str = null;
		String link = null;
		Node node = null;

		for (int i = 0; i < nl.size(); i++) {
			node = nl.elementAt(i);
			str = nl.elementAt(i).toPlainTextString().trim();

			if (node instanceof Tag) {
				link = ((Tag) node).getAttribute("href");
				link = link.substring(6);
				linkMap.put(fixUmlauts(str), URLprefix + link);
			}
		}

		System.out.println(linkMap);

	}

	private String fixUmlauts(String input) {
		String newString = null;
		newString = input.replace("&Auml;", "�");
		newString = newString.replace("&auml;", "�");
		newString = newString.replace("&Uuml;", "�");
		newString = newString.replace("&uuml;", "�");
		newString = newString.replace("&Ouml;", "�");
		newString = newString.replace("&ouml;", "�");
		return newString;
	}
}
