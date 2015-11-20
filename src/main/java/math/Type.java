package math;

public enum Type {

	PLUS("+"),
	MINUS("-"),
	MULTIPLY("*"),
	INT("i"),
	FLOAT("f");
	
	private String type;
	
	private Type(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
