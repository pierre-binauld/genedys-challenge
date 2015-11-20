package main;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import regex.Regex;
import regex.RegexAnalyzer;
import web.GenedysAccessor;
import web.WebAccessor;
import web.WebAccessorException;

public class RegularSolver {

	private static final String URL = "http://defi.genedys.com/challenge/backend/regexp/api";
	private static final String EXPRESSION = "expression";
	private static final int N = 3;

	public static void main(String[] args) throws WebAccessorException {

		RegularSolver solver = new RegularSolver();
		solver.run();
	}

	public void run() throws WebAccessorException {
		
		WebAccessor webAccessor = new WebAccessor();
		GenedysAccessor genedysAccessor = new GenedysAccessor(webAccessor);

		String content = genedysAccessor.getChallengeContent(URL);
		String expression = getExpression(content);
		
		RegexAnalyzer analyzer = new RegexAnalyzer();
		Regex regex = analyzer.analyze(expression);
		List<String> results = regex.resolve(N);
		
		String challengeResponse = toJson(results);
		String response = genedysAccessor.sendChallengeResponse(URL, challengeResponse);

		System.out.println(content);
		System.out.println(expression);
		for (String result : results) {
			System.out.println(result);
		}
		System.out.println(verify(expression, results));
		System.out.println(response);
	}
	
	public String getExpression(String challengeContent) {
		
		JsonObject jsonObject = new JsonParser().parse(challengeContent).getAsJsonObject();
		return jsonObject.get(EXPRESSION).getAsString();
	}
	
	public boolean verify(String regex, List<String> results){
		
		Pattern pattern = Pattern.compile(regex);
		
		for (String result : results) {
			Matcher matcher = pattern.matcher(result);
			if ( ! matcher.find() )
			{
				System.out.println("Error: " + result);
				return false;
			}
		}
		
		return true;
	}
	
	public String toJson(List<String> results) {
		JsonArray jsonArray = new JsonArray();
		for (String result : results) {
			jsonArray.add(result);
		}
		return jsonArray.toString();
	}
}
