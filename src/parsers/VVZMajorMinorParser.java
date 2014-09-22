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

public class VVZMajorMinorParser {

	String URLPrefix = null;
	String URL = null;
	
	HashMap<String, String> studiesMap = new HashMap<String, String>();
	HashMap<String, List<String>> facultyMap = new HashMap<String, List<String>>();

	public VVZMajorMinorParser(String url, String prefix) throws ParserException {
		URL = url;
		URLPrefix = prefix;
	}

	public void parseMajorMinor() throws ParserException{
		ArrayList<Map<String, Map<String, String>>> result = new ArrayList<Map<String, Map<String, String>>>();
		
		HasAttributeFilter filter = new HasAttributeFilter("class", "linked noimage");
		Parser parser = new Parser();
		parser.setResource(URL);
		
		NodeList nl = parser.parse(filter);
		for(int i=0; i < nl.size(); i++){
			HashMap<String, HashMap<String, String>> majorMinorMap = new HashMap<String, HashMap<String,String>>();
			
			Node rootMajorMinor = nl.elementAt(i);
			Node majorMinor = rootMajorMinor.getFirstChild();
			Node firstStudy = rootMajorMinor.getNextSibling().getFirstChild();

			String majorMinorString = majorMinor.toPlainTextString().trim();
			System.out.println(majorMinorString);
			System.out.println("----------------------------");
			Node current = firstStudy.getNextSibling();
			while((current = current.getNextSibling())!=null){
				System.out.println(current.toPlainTextString().trim());
			}
			System.out.println("_____________________________");
		
		}
		
				
		
	}
	
	public Map<String, List<String>> parseFaculties() throws ParserException {
		HasAttributeFilter f = new HasAttributeFilter("class", "links");
		Parser parser = new Parser();
		parser.setResource(URL);
		NodeList nl = parser.parse(f);
		String faculty = null;
		String facName = null;

		for (int i = 0; i < nl.size(); i++) {

			Node node = nl.elementAt(i);
			faculty = node.getFirstChild().getFirstChild().getFirstChild().getText().trim();
			if(faculty.contains("&auml;")){
				facName = faculty.replace("&auml;", "ä");
			}else{
				facName = faculty;
			}
			
			
			NodeList childList = node.getChildren();
			NodeList listChilds = childList.elementAt(1).getChildren();
			
			List<String> list = new ArrayList<String>();
			for(int j = 0 ; j < listChilds.size(); j++){
				String str = listChilds.elementAt(j).toPlainTextString().trim();
				String name = null;
				
				if (!str.contains("&uuml;") && !str.contains("&ouml;")
						&& !str.contains("&Auml") && !str.contains("Weiter")
						&& !str.contains("Double") && !str.contains("Joint")) {
					
					if(str.contains("&auml;")){
						name = str.replace("&auml;", "ä");
						list.add(name);
					}else{
						list.add(str);
					}
					
				}
			}
			facultyMap.put(facName, list);
		}
		return facultyMap;
	}
	
	public Map<String, String> parseStudies() throws ParserException {
		HasAttributeFilter f = new HasAttributeFilter("class", "internal");
		Parser parser = new Parser();
		parser.setResource(URL);
		NodeList nl = parser.parse(f);
		String str = null;
		String link = null;

		for (int i = 0; i < nl.size(); i++) {

			Node node = nl.elementAt(i);
			str = node.getFirstChild().getText().trim();

			if (!str.contains("&uuml;") && !str.contains("&ouml;")
					&& !str.contains("&Auml") && !str.contains("Weiter")
					&& !str.contains("Double") && !str.contains("Joint")
					&& !str.contains("Haupt") && !str.contains("fach")) {

				if(str.contains("Fakult&auml;t") || str.contains("&auml;")){
					if(str.contains("Master") || str.contains("Bachelor") || str.contains("Doktor") || str.contains("PhD")){
						if(str.contains("&auml;")){str = str.replace("&auml;", "ä");}
						if (node instanceof Tag) {
							link = ((Tag) node).getAttribute("href");
							if(link.contains("../../")){
								link = link.replace("../../", "");
							}
							studiesMap.put(str, URLPrefix + link);
						}
					}
				}else{
					if (node instanceof Tag) {
						link = ((Tag) node).getAttribute("href");
						if(link.contains("../../")){
							link = link.replace("../../", "");
						}
						studiesMap.put(str, URLPrefix + link);
					}
				}

			}
		}
		return studiesMap;
	}
}
