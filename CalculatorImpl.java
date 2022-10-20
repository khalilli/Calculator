//implementation of the Calculator Interface
public class CalculatorImpl implements Calculator {
	
	//default constructor
	public CalculatorImpl() {
		super();
	}

	public int sum(int x, int y) {
		return x+y;
	}

	public int subtraction(int x, int y) {
		return x-y;
	}

	public int product(int x, int y) {
		return x*y;
	}

	public int division(int x, int y) {
		return x/y;
	}

}
