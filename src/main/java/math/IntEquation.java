package math;

public class IntEquation implements Equation {
	
	private int num;
	
	public IntEquation(int num) {
		this.num = num;
	}

	public float solve() {
		return num;
	}

}
