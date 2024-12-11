public class Ramanujan {
	public static void main(String[] args) {
		Factorial f = new Factorial();
		int n = Integer.parseInt(args[0]);
		double sum = 0;
		double constant = 2*Math.sqrt(2)/9801;
		for (int k=0; k<=n; k++) {
			double numerator = f.calculate(4*k)*(1103+26390*k);
			double denominator = Math.pow(f.calculate(k),4)*Math.pow(396, 4*k);
			//double nthTerm = (((2*Math.sqrt(2))/9801)*((f.calculate(4*k)*(1103+26390*k))/(Math.pow(f.calculate(k),4)*Math.pow(396,4*k))));
			double nthTerm = constant*numerator/denominator;
			sum += nthTerm;
		}
		sum = 1/sum;
		double errorBound = 100 - (sum/Math.PI)*100;
		System.out.println("Pi according to Ramanujan series: "+sum);
		System.out.println("This is error bound from Java's value by "+errorBound+" percent");
	}
}