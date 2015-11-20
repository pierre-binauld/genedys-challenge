package regex;

import java.util.ArrayList;
import java.util.List;

public abstract class MultiRegex implements RegexResolver {

	protected String pattern;

	private boolean isMultiple = false;
	private String numeration;

	public MultiRegex(String pattern) {
		this.pattern = pattern;

		String lastChar = pattern.substring(pattern.length() - 1);

		if (Numeration.PLUS.equals(lastChar) 
				|| Numeration.QUESTION.equals(lastChar)
				|| Numeration.STAR.equals(lastChar)) {
			this.numeration = lastChar;
			this.isMultiple = true;
			this.pattern = pattern.substring(0, pattern.length() - 1);
		}
	}

	public boolean isMultiple() {
		return isMultiple;
	}

	public List<String> resolve(int n) {

		List<String> subResult = onResolve(n);
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < n; i++) {
			result.add("");
		}

		if (Numeration.STAR.equals(numeration)) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < i; j++) {
					result.set(i, result.get(i) + subResult.get(i));
				}
			}
			
		} else if (Numeration.PLUS.equals(numeration)) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < i+1; j++) {
					result.set(i, result.get(i) + subResult.get(i));
				}
			}
			
			 
		} else if (Numeration.QUESTION.equals(numeration)) {
			for (int i = 0; i < n; i++) {
				if( i % 2 == 0 ) {
					result.set(i, result.get(i) + subResult.get(i));
				}
			}

		} else {
			for (int i = 0; i < n; i++) {
				result.set(i, result.get(i) + subResult.get(i));
			}
		}

		return result;
	}
	
	protected abstract List<String> onResolve(int n);
}
