package regex;

import java.util.ArrayList;
import java.util.List;

public class AlphaNumRegex extends MultiRegex {
	
	public AlphaNumRegex(String pattern) {
		super(pattern);
	}

	@Override
	protected List<String> onResolve(int n) {
		List<String> result = new ArrayList<String>();
		
		if(pattern.length() == 1) {
			for (int i = 0; i < n; i++) {
				result.add(pattern);
			}
			
		} else {
			for (int i = 0; i < n; i++) {
				result.add("");
			}
		}
		
		return result;
	}

}
