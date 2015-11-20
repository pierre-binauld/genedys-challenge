package main;

import web.GenedysAccessor;
import web.WebAccessor;
import web.WebAccessorException;

public class RegularSolver {

	private static final String URL = "http://defi.genedys.com/challenge/backend/regexp/api";

	public static void main(String[] args) throws WebAccessorException {

		WebAccessor webAccessor = new WebAccessor();
		GenedysAccessor genedysAccessor = new GenedysAccessor(webAccessor);
		
		String content = genedysAccessor.getChallengeContent(URL);
		
		System.out.println(content);
	}

}
