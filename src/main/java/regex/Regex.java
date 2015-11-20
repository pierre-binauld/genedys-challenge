package regex;

import java.util.ArrayList;
import java.util.List;

public class Regex extends ArrayList<RegexResolver> implements RegexResolver {

	public List<String> resolve(int n) {
		
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < n; i++) {
			result.add("");
		}
		
		for (RegexResolver resolver : this) {
			List<String> part = resolver.resolve(n);
			for (int i = 0; i < n; i++) {
				result.set(i, result.get(i) + part.get(i));
			}
		}
		
		return result;
	}
	
}
