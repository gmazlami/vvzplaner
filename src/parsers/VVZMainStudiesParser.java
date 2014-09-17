package parsers;

import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class VVZMainStudiesParser {

	private String resourceURL = null;
	private Parser parser = null;
	
	public VVZMainStudiesParser(String url) throws ParserException{
		this.resourceURL = url;
		this.parser = new Parser();
		this.parser.setURL(resourceURL);
	}
	
	public void parse() throws ParserException{
		HasAttributeFilter f = new HasAttributeFilter("class", "linked noimage");
		NodeList nl = parser.parse(f);
		for(int i = 0; i < nl.size(); i++){
			System.out.println(nl.elementAt(i).getFirstChild().getFirstChild().getText().trim());
		}
	}
	
	
}
