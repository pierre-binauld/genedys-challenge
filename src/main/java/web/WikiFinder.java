package web;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WikiFinder {

	private static final String URL_1 = "https://en.wikipedia.org/wiki/%s_(programming_language)";
	private static final String URL_2 = "https://en.wikipedia.org/wiki/%s";

	private static final String DATE_CELL_TITLE = "First appeared";
	private static final String PATTERN			= "[0-9]{4}";
	
	public Number getLanguageCreationDate(String language) throws IOException {
		
		Document doc = getLanguagePage(language);
		
		Elements infobox = doc.select(".infobox tr");
		
		for (Element element : infobox) {
			Elements th = element.select("th");
			if(th.hasText()) {
				String title = th.text();
				if(DATE_CELL_TITLE.equals(title)) {
					Elements td = element.select("td");
					if(td.hasText()) {
						Pattern pattern = Pattern.compile(PATTERN);
						Matcher matcher = pattern.matcher(td.text());
						if (matcher.find())
						{
							return Integer.parseInt(matcher.group(0));
						}
					}
				}
			}
		}
		
		return 0;
	}
	
	private Document getLanguagePage(String language) throws IOException {
		
		try {
			String url = String.format(URL_1, language);
			Document doc = Jsoup.connect(url).get();
			return doc;
			
		} catch (IOException e) {
			String url = String.format(URL_2, language);
			Document doc = Jsoup.connect(url).get();
			return doc;
		}
		
		
	}
}
