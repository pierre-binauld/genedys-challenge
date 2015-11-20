package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexAnalyzer {

	private static final String REGEX_GROUP 	= "(\\([^\\)]*\\)[\\?\\*\\+]?)";
	private static final String REGEX_SET 		= "(\\[[^\\]]*\\][\\?\\*\\+]?)";
	private static final String REGEX_ALPHA_NUM = "(.[\\?\\*\\+]?)";
	private static final String REGEX	 		= "("+ REGEX_GROUP +"|"+ REGEX_SET +"|"+ REGEX_ALPHA_NUM +")";

	public Regex analyze(String regexString) {
		
		Pattern groupPattern = Pattern.compile(REGEX);
		Matcher matcher = groupPattern.matcher(regexString);
		Regex regex = new Regex();
		
		while(matcher.find()) {
			String pattern = matcher.group();
			String firstChar = pattern.substring(0, 1);
			
			if("^".equals(firstChar) || "$".equals(firstChar)) {
				// Do nothing
			} else if ("(".equals(firstChar)) {
				regex.add(new GroupRegex(pattern));
			} else if ("[".equals(firstChar)) {
				regex.add(new SetRegex(pattern));
			} else {
				regex.add(new AlphaNumRegex(pattern));
			}
		}
		
		return regex;
	}
}
