package web;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

public class WebAccessor {

	public String get(String webUrl, Map<String, String> properties) throws WebAccessorException {

		BufferedReader in = null;
		try {
			URL url = new URL(webUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setRequestMethod("GET");
			
			for (Entry<String, String> property : properties.entrySet()) {
				con.setRequestProperty(property.getKey(), property.getValue());
			}

			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			
			return response.toString();
			
		} catch (IOException e) {
			throw new WebAccessorException(e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					throw new WebAccessorException(e);
				}				
			}
		}
	}
	
	public String post(String webUrl, String webResponse, Map<String, String> properties) throws WebAccessorException {
		BufferedReader in = null;
		DataOutputStream wr = null;
		try {
			URL url = new URL(webUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setRequestMethod("POST");

			for (Entry<String, String> property : properties.entrySet()) {
				con.setRequestProperty(property.getKey(), property.getValue());
			}
			
			con.setDoOutput(true);
			wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(webResponse);
			wr.flush();
			wr.close();

			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			return response.toString();
			
		} catch (IOException e) {
			throw new WebAccessorException(e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					throw new WebAccessorException(e);
				}
				try {
					wr.close();
				} catch (IOException e) {
					throw new WebAccessorException(e);
				}
			}
		}
	}
}
