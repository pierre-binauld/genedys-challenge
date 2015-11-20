package main;

import java.io.IOException;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import web.GenedysAccessor;
import web.WebAccessor;
import web.WebAccessorException;
import web.WikiFinder;

public class WikiCrawler {

	private static final String URL = "http://defi.genedys.com/challenge/backend/crawler/api";

	private static final String LANGUAGE = "language";
	private static final String CREATION = "creation";

	public static void main(String[] args) throws WebAccessorException, IOException {

		WikiCrawler crawler = new WikiCrawler();
		crawler.run();
		
	}
	
	public void run() throws IOException, WebAccessorException {
		
		WebAccessor webAccessor = new WebAccessor();
		GenedysAccessor genedysAccessor = new GenedysAccessor(webAccessor);
		WikiFinder wikiFinder = new WikiFinder();
		
		String content  = genedysAccessor.getChallengeContent(URL);
		String language = getLanguage(content);

		Number date = wikiFinder.getLanguageCreationDate(language);

		String challengeResponse = toJson(date);
		String response = genedysAccessor.sendChallengeResponse(URL, challengeResponse);

		System.out.println(content);
		System.out.println(language);
		System.out.println(date);
		System.out.println(challengeResponse);
		System.out.println(response);
	}
	
	public String getLanguage(String challengeContent) {
		
		JsonObject jsonObject = new JsonParser().parse(challengeContent).getAsJsonObject();
		return jsonObject.get(LANGUAGE).getAsString();
	}
	
	public String toJson(Number date) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty(CREATION, date);
		return jsonObject.toString();
	}

}
