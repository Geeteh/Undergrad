public class FunctionTest {
	
	public static double findRoot(double a, double b, double epsilon) {
		double x = (a+b)/2;
		if (Math.abs(a-x) < epsilon) return x;
		else if ((Math.sin(x) > 0 && Math.sin(a) > 0) || (Math.sin(x) < 0 && Math.sin(a) < 0)) return findRoot(x, b, epsilon);
		else return findRoot(a, x, epsilon);
	}
	
	public static void main(String[] args) {
		
		System.out.println(findRoot(3, 4, 0.00000001));
	}
}