import java.util.ArrayList;
import java.util.HashMap;

public class Poly {
	
	public int[] coefficients;
	
	public Poly(int[] c) {
		int[] reversed = reverse(c, c.length);
		coefficients = reversed;
	}
	
	public int[] reverse(int[] arr, int arr_length) {
		int[] reversedArr = new int[arr_length];
		for (int i = 0; i < arr_length; i++) {
			reversedArr[arr_length - 1 - i] = arr[i];
		}
		return reversedArr;
	}
	
	public int degree() {
		int highestDegree = 0;
		for (int i = 0; i<coefficients.length; i++) {
			if (coefficients[i] != 0) highestDegree = i;
		}
		return highestDegree;
	}
	
	public int[] toArray(ArrayList<Integer> list) {
		int[] arr = new int[list.size()];
		for (int i = 0; i<list.size(); i++) {
			arr[i] = list.get(i);
		}
		return arr;
	}
	
	@Override
	public String toString() {
		String expression = "";
		coefficients = reverse(coefficients, coefficients.length);
		for (int i = degree(); i >= 0; i--) {
			if (coefficients[i] != 0) {
				if (coefficients[i] > 0 && expression != "") expression += "+";
				if (i == 0) expression += coefficients[i];
				else if (i == 1) expression += coefficients[i] + "x";
				else expression += coefficients[i] + "x^" + i;
			}
		}
		return expression;
	}
	
	public Poly add(Poly a) {
		ArrayList<Integer> sum = new ArrayList<>();
		a.coefficients = reverse(a.coefficients, a.coefficients.length);
		coefficients = reverse(coefficients, coefficients.length);
		int[] shorterPoly = new int[Math.min(coefficients.length, a.coefficients.length)];
		int[] longerPoly = new int[Math.max(coefficients.length, a.coefficients.length)];
		int shorterPolyDegree;
		int longerPolyDegree;
		if (coefficients.length > a.coefficients.length) {
			longerPoly = coefficients;
			longerPolyDegree = degree();
			shorterPoly = a.coefficients;
			shorterPolyDegree = a.degree();
		} else {
			longerPoly = a.coefficients;
			longerPolyDegree = a.degree();
			shorterPoly = coefficients;
			shorterPolyDegree = degree();
		}
		boolean flag = true;
		for (int i = longerPolyDegree; i >= 0; i--) {
			for (int j = shorterPolyDegree; j >= 0; j--) {
				if (longerPoly[i] != 0 && shorterPoly[j] != 0) {
					if (i == j) {
						if (!sum.contains(shorterPoly[i] + shorterPoly[j]) && flag == true) {
							sum.add(longerPoly[i] + shorterPoly[j]);
							shorterPoly = reverse(shorterPoly, shorterPoly.length);
							System.out.println(sum);
							flag = false;
							break;
						}
					} else if (i < j) {
						if (!sum.contains(shorterPoly[j])) {
							sum.add(shorterPoly[j]); System.out.println(sum);
						}
					} else if (i > j) {
						if (!sum.contains(longerPoly[i])) {
							sum.add(longerPoly[i]); System.out.println(sum);
						}
					}
				}
			}
		}
		//System.out.println(sum);
		int[] returnArray = new int[sum.size()];
		returnArray = toArray(sum);
		returnArray = reverse(returnArray, returnArray.length);
		return new Poly(returnArray);
	}
	
	public double evaluate(double x) {
		double value = 0;
		coefficients = reverse(coefficients, coefficients.length);
		for (int i = degree(); i >= 0; i--) {
			if (coefficients[i] != 0 ) {
				value += coefficients[i]*Math.pow(x,i);
			}
		}
		return value;
	}
	
	public static void main(String[] args) {
		int arr1[] = {4,0,-8,0,3,2}; //2, 3, 0, ,-8,
		int arr2[] = {0,-2,4,1};
		Poly poly1 = new Poly(arr1);
		Poly poly2 = new Poly(arr2);
		/*for (int i = 0; i<poly1.coefficients.length; i++) {
			System.out.println(poly1.coefficients[i]);
		}
		for (int i = 0; i<poly2.coefficients.length; i++) {
			System.out.println(poly2.coefficients[i]);
		}*/
		//System.out.println(poly1.degree());
		//System.out.println(poly1.toString());
		//System.out.println(poly1.length);
		//System.out.println(poly2.length);
		//System.out.println(poly1.add(poly2));
		System.out.println(poly1.evaluate(2));
	}
}









//////////////////////////////ignore//////////////////////
	/*
	public HashMap<Integer,Integer> getDegreesMap(int[] c) {
		HashMap<Integer, Integer> degreesMap = new HashMap<>();
		c = reverse(c, c.length);
		for (int i = 0; i < c.length; i++) {
			if (c[i] != 0) degreesMap.put(c[i], i);
		}
		return degreesMap;
	}
	
	public int[] getDegreesArray(int[] c) {
		int nonzeroCoefficients = 0;
		for (int i = 0; i < c.length; i++) {
			if (c[i] != 0) nonzeroCoefficients++;
		}
		ArrayList<Integer> currentDegrees = new ArrayList<>();
		c = reverse(c, c.length); //used in the instance method Poly.add(), so reverse() returns original array where nonzero c[2] is x^2
		for (int j = 0; j < c.length; j++) {
			if (c[j] != 0) currentDegrees.add(j);
		}
		int[] returnArray = toArray(currentDegrees);
		returnArray = reverse(returnArray, returnArray.length);
		return returnArray;
	}
	*/
////////////////////////////////////////////////////////