import java.math.BigDecimal;
import java.math.RoundingMode;
public class RamanujanBig {
	
	public BigDecimal evaluateBig(int x) {
		//a polynomial with x = sqrt(some #) for x > 0 has coefficient array of {-x,0,1}.  ie: x^2 - 2 has x = sqrt2 and coef array {-2,0,1}
		int[] coef = {-x, 0, 1};
		PolyFunc polynomial = new PolyFunc(coef);
		BigDecimal sqrt = BigDecimal.valueOf(polynomial.findRoot(Math.round(Math.sqrt(x))-1, x, 0.00000000000000000000000001));
		System.out.println(sqrt);
		return sqrt;
		/*alternate solution
		BigDecimal sqrt = BigDecimal.valueOf(Math.sqrt(x));
		return sqrt;
		*/
	}
	
	public static void main(String[] args) {
		Factorial f = new Factorial();
		int n = Integer.parseInt(args[0]);
		BigDecimal sum = new BigDecimal(0);
		RamanujanBig sqrt = new RamanujanBig(); 
		BigDecimal constant = BigDecimal.valueOf(2).multiply(sqrt.evaluateBig(2)).divide(BigDecimal.valueOf(9801),50,RoundingMode.HALF_UP);		
		for (int k=0; k<=n; k++) {
			BigDecimal numerator = f.calculateBig(4*k).multiply(BigDecimal.valueOf(1103+26390*k));                                  //f.calculate(4*k)*(1103+26390*k));
			BigDecimal denominator = f.calculateBig(k).pow(4).multiply(BigDecimal.valueOf(396).pow(4*k));                                    //(Math.pow(f.calculate(k),4)*Math.pow(396, 4*k));
			BigDecimal nthTerm = constant.multiply(numerator).divide(denominator,50,RoundingMode.HALF_UP);
			sum = sum.add(nthTerm);
		}
		sum = BigDecimal.valueOf(1).divide(sum,50,RoundingMode.HALF_UP);
		BigDecimal errorBound = BigDecimal.valueOf(100).subtract((sum.divide(BigDecimal.valueOf(Math.PI),50,RoundingMode.HALF_UP)).multiply(BigDecimal.valueOf(100)));
		System.out.println("Pi according to Ramanujan series: "+sum);
		System.out.println("This is error bound from Java's value by "+errorBound+" percent");
	}
}