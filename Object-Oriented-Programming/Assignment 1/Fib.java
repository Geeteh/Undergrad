public class Fib {
	public static void main(String[] args) {
		int arg = Integer.parseInt(args[0]);
		int a = 1;
		int b = 1;
		for (int i=1; i<arg; i++) {
			int sum = a + b;
			a = b;
			b = sum;
		}
		System.out.println(a);
	}
}