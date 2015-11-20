package regex;

import java.util.ArrayList;
import java.util.List;

public class SetRegex extends MultiRegex {

	private AlphaNumHolder alphaNumHolder = new AlphaNumHolder();
	
	private String set;
	private boolean negative = false;

	public SetRegex(String pattern) {
		super(pattern);

		set = this.pattern.substring(1, this.pattern.length() - 1);

		String firstChar = set.substring(0, 1);
		if ("^".equals(firstChar)) {
			set = set.substring(1, set.length());
			negative = true;
		}

	}

	@Override
	protected List<String> onResolve(int n) {
		List<String> result = new ArrayList<String>();

		for (int i = 0; i < n; i++) {
			if (negative) {
				result.add(alphaNumHolder.next(set)+"");
			} else {
					int j = i % set.length();
					result.add(set.charAt(j) + "");
			}
		}

		return result;
	}

}
