package parsers;

import io.Utils;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
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
		
		
		Parser parser = new Parser();
		parser.setResource(resourceURL);
//		parseDocent(parser);
//		parsePointsDescriptionExam(parser);
		String link = parseDetailsLink(parser);
		Parser locationParser = new Parser(correctDetailsLink(resourceURL, link));
		
		
	}
	
	public String parseDetailsLink(Parser parser) throws ParserException{
		TagNameFilter f = new TagNameFilter("acronym");
		NodeList nl = parser.parse(f);
		Node detailsLink = nl.elementAt(0).getParent().getPreviousSibling().getFirstChild();
		String link  = null;
		
		if(detailsLink instanceof Tag){
			Tag detailsLinkTag = (Tag)detailsLink;
			link = detailsLinkTag.getAttribute("href");
		}
		
		return link;
	}
	
	public String[] parsePointsDescriptionExam(Parser parser) throws ParserException {
		TagNameFilter f = new TagNameFilter("td");
		NodeList nl = parser.parse(f);
		String[] pointsDescExam = new String[3];
		pointsDescExam[0] = Utils.fixUmlauts(nl.elementAt(1).toPlainTextString().trim()); //points
		pointsDescExam[1] = Utils.fixUmlauts(nl.elementAt(3).toPlainTextString().trim()); //description
		pointsDescExam[2] = Utils.fixUmlauts(nl.elementAt(9).toPlainTextString().trim()); //exam info
		return pointsDescExam;
	}
	
	public String parseDocent(Parser parser) throws ParserException{
		HasAttributeFilter f = new HasAttributeFilter("style", "white-space: nowrap");
		NodeList nl = parser.parse(f);
		Node node = nl.elementAt(0).getParent().getParent().getNextSibling();
		return Utils.fixUmlauts(node.toPlainTextString().trim());
	}
	
	public String[] parseDayTime(Parser parser) throws ParserException {
		HasAttributeFilter f = new HasAttributeFilter("style",
				"white-space: nowrap");
		NodeList nl = parser.parse(f);

		Node node = null;
		String str = null;
		node = nl.elementAt(0);
		str = node.toPlainTextString().trim();
		String[] dayTime = str.split(" ");
		String[] time = dayTime[1].split("-");
		String[] dayBeginEndTime = { dayTime[0], time[0], time[1] };
		return dayBeginEndTime;
	}
	
	private String correctDetailsLink(String prefix, String postfix){
		return prefix.replace(".modveranst.html", "")+"/"+postfix;
	}
}
