package web;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class GenedysAccessor {

	private static final String PARAM_TOKEN = "ApiToken";

	private static final String TOKEN = "968ac4795a9ed5a0850f05aed292114e";
	
	private WebAccessor 		webAccessor;
	private Map<String, String> properties = new HashMap<String, String>();

	public GenedysAccessor(WebAccessor webAccessor) {
		this.webAccessor = webAccessor;
		this.properties  = new HashMap<String, String>();
		properties.put(PARAM_TOKEN, TOKEN);
	}

	public String getChallengeContent(String challengeUrl) throws WebAccessorException {

		return webAccessor.get(challengeUrl, properties);
	}
	
	public String sendChallengeResponse(String challengeUrl, String challengeResponse) throws WebAccessorException {

		return webAccessor.post(challengeUrl, challengeResponse, properties);
	}
}
