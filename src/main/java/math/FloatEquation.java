package math;

public class FloatEquation implements Equation {
	
	private float num;
	
	public FloatEquation(float num) {
		this.num = num;
	}

	public float solve() {
		return num;
	}

}
