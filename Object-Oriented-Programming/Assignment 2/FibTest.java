public class FibTest {
	public static int fibIter(int n) {
		int a = 1;
		int b = 1;
		for (int i=1; i<n; i++) {
			int sum = a+b;
			a=b;
			b=sum;
		}
		return a;
	}
	public static int fibRecur(int n) {
		if (n == 1 || n == 2) return 1;
		else return fibRecur(n-1) + fibRecur(n-2);
	}
	public static void main(String[] args) {
		//Test ~ f(4) = 3
		if (fibRecur(4) != 3 && fibIter(4) != 3) System.out.println("Fail");
		
		long start_fibIter = System.currentTimeMillis();
		fibIter(40);
		long end_fibIter = System.currentTimeMillis() - start_fibIter;
		System.out.println("ms to execute FibTest.fibIter(40): " + end_fibIter);
		
		long start_fibRecur = System.currentTimeMillis();
		fibRecur(40);
		long end_fibRecur = System.currentTimeMillis() - start_fibRecur;
		System.out.println("ms to execute FibTest.fibRecur(40): " + end_fibRecur);
	}
}