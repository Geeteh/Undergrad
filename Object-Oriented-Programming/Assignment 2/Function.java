public abstract class Function {
	public abstract double evaluate(double x);
	
	public double findRoot(double a, double b, double epsilon) {
		double x = (a+b)/2;
		if (Math.abs(a-x) < epsilon) return x;
		else if ((evaluate(x) > 0 && evaluate(a) > 0) || (evaluate(x) < 0 && evaluate(a) < 0)) return findRoot(x, b, epsilon);
		else return findRoot(a, x, epsilon);
	}
	
	public static void main(String[] args) {
		Function sin = new SinFunc();
		System.out.println(sin.findRoot(3, 4, 0.00000001));
		
		Function cos = new CosFunc();
		System.out.println(cos.findRoot(1, 3, 0.00000001));
		
		int[] coef1 = {-3, 0, 1};
		int[] coef2 = {-2, -1, 1};
		Function polynomial1 = new PolyFunc(coef1);
		Function polynomial2 = new PolyFunc(coef2);
		
		System.out.println(polynomial1.findRoot(1,2, 0.000000001));
		System.out.println(polynomial2.findRoot(1,3, 0.000000001));
	}
}