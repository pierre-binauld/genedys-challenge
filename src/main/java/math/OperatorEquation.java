package math;

public class OperatorEquation implements Equation {

	private Equation eq1;
	private Equation eq2;
	private Operator operator;

	public OperatorEquation(Operator operator, Equation eq1, Equation eq2) {
		super();
		this.eq1 = eq1;
		this.eq2 = eq2;
		this.operator = operator;
	}


	public float solve() {

		switch (operator) {
		case PLUS:
		default:
			return eq1.solve() + eq2.solve();
			
		case MINUS:
			return eq1.solve() - eq2.solve();
			
		case MULTIPLY:
			return eq1.solve() * eq2.solve();
		}
	}

}
