package math;

public enum Operator {

	PLUS("+"),
	MINUS("-"),
	MULTIPLY("*");
	
	private String type;
	
	private Operator(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
