package regex;

public class AlphaNumHolder {
	
	private char[] alphaNum = new char[] {
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
		};
	
	private int index = -1;
	
	public char next() {
		index++;
		index = index % alphaNum.length;
		return alphaNum[index];
	}
	
	public char next(String except) {
		
		char result = next();
		while(except.contains(result+"")) {
			result = next();
		}
		
		return result;
	}
}
