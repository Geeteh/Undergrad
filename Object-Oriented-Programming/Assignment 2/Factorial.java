import java.math.BigDecimal;
public class Factorial {
	public static long calculate(long n) {
		if (n == 0) { return 1; }
		else if (n < 0 || n > 20) {
			System.out.println("Error: Factorial.calculate arg n bounded by 0 <= n <= 20");
			System.exit(0);
		}
		return n*calculate(n-1);
	}
	
	//BigDecimal
	public static BigDecimal calculateBig(long n) {
		if (n == 0) return BigDecimal.valueOf(1);
		else if (n < 0 || n > 20) {
			System.out.println("Error: Factorial.calculateBig arg n bounded by 0 <= n <= 20");
			System.exit(0);
		}
		return BigDecimal.valueOf(n).multiply(calculateBig(n-1));
	}
	
	public static void main(String[] args) {
		System.out.println("Factorial.calculateBig(2) = "+calculateBig(2));
		System.out.println("Factorial.calculate(0) = "+calculate(0));
		System.out.println("Factorial.calculate(5) = "+calculate(5));
		System.out.println("Factorial.calculate(-4) = "+calculate(-4));
	}
}

