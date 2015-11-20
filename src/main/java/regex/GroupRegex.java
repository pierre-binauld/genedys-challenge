package regex;

import java.util.ArrayList;
import java.util.List;

public class GroupRegex extends MultiRegex {
	
	private String[] groups;
	
	public GroupRegex(String pattern) {
		super(pattern);
		
		String subPattern = this.pattern.substring(1, this.pattern.length() - 1);
		
		this.groups = subPattern.split("\\|");
	}

	@Override
	protected List<String> onResolve(int n) {
		List<String> result = new ArrayList<String>();
		
		for (int i = 0; i < n; i++) {
			int j = i % groups.length;
			result.add(groups[j]);
		}
		
		return result;
	}
}
