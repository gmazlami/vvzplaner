package parsers;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class VVZDetailsParser {

	private String resourceURL = null;
	private String URLprefix = null;
	
	public VVZDetailsParser(String url, String prefix){
		this.resourceURL = url;
		this.URLprefix = prefix;
	}
	
	public void parse() throws ParserException{
		HasAttributeFilter f = new HasAttributeFilter("style", "white-space: nowrap");
		Parser parser = new Parser();
		parser.setResource(resourceURL);
		NodeList nl = parser.parse(f);
		
		Node node = null;
		String str = null;
		
		for(int i=0; i < nl.size();i++){
			node = nl.elementAt(i);		
			str = node.toPlainTextString().trim();
			String[] dayTime = str.split(" ");
			String[] time = dayTime[1].split("-");
			
			System.out.println("Day:" + dayTime[0]);
			System.out.println("Begin: " + time[0] + "  End: " + time[1]);
		}
	}
}
