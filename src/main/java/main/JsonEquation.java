package main;

import com.google.gson.JsonObject;

import math.Equation;
import math.EquationFactory;
import web.GenedysAccessor;
import web.WebAccessor;

public class JsonEquation {

	private static final String URL = "http://defi.genedys.com/challenge/backend/operation/api";

	public static void main(String[] args) throws Exception {
		
		WebAccessor webAccessor = new WebAccessor();
		GenedysAccessor genedysAccessor = new GenedysAccessor(webAccessor);
		EquationFactory equationFactory = new EquationFactory();
		
		String content = genedysAccessor.getChallengeContent(URL);
		
		Equation equation = equationFactory.fromJson(content);
		float result = equation.solve();
		
		JsonObject jsonResult = equationFactory.toJson(result);
		
		String response = genedysAccessor.sendChallengeResponse(URL, jsonResult.toString());
		
		System.out.println(content);
		System.out.println(result);
		System.out.println(jsonResult);
		System.out.println(response);
	}

}
