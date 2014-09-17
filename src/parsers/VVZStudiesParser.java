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

	HasAttributeFilter f = new HasAttributeFilter("class", "internal");

	String URLPrefix = null;

	Parser parser = null;

	HashMap<String, String> map = new HashMap<String, String>();

	public VVZStudiesParser(String htmlCode, String prefix) throws ParserException {
		parser = new Parser(htmlCode);
		URLPrefix = prefix;
	}

	public Map<String, String> parse() throws ParserException {
		// StringBuilder sb = new StringBuilder();
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

					map.put(str, URLPrefix + link);
				}

			}
		}
		// return sb.toString();
		// System.out.println(map);
		return map;
	}
}
