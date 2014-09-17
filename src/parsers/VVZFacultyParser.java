package parsers;

import java.util.HashMap;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class VVZFacultyParser {

	private String resourceURL = null;
	private String URLprefix = null;
	private Parser parser = null;
	private HashMap<String, String> map = new HashMap<String,String>();
	
	public VVZFacultyParser(String url, String prefix) throws ParserException{
		this.resourceURL = url;
		this.URLprefix = prefix;
		this.parser = new Parser();
		this.parser.setURL(resourceURL);
	}
	
	public void parse() throws ParserException{
		
		HasAttributeFilter f = new HasAttributeFilter("class", "linked noimage");
		NodeList nl = parser.parse(f);
		
		String link = null;
		
		String faculty = null;
		
		for(int i = 0; i < nl.size(); i++){
//			System.out.println(nl.elementAt(i).getFirstChild().getText());
			Node node = nl.elementAt(i).getFirstChild();
			if(node instanceof Tag){
				link = ((Tag) node).getAttribute("href");
				System.out.println(URLprefix + link);
			}
			
			
			System.out.println(nl.elementAt(i).getFirstChild().getFirstChild().getText().trim());
			System.out.println();
		}
	}
	
}
