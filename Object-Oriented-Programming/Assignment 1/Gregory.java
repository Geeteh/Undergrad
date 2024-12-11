public class Gregory {
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		double sum = 0;
		for (int k=1; k<=n; k++) {
			double nthTerm = ( -4*Math.pow(-1,k)  ) / ( 2*k - 1 );
			sum += nthTerm;
		}
		double errorBound = 100 - (sum/Math.PI)*100;
		System.out.println("Pi according to Gregory series: "+sum);
		System.out.println("This is error bound from Java's value by "+errorBound+" percent");
	}
}