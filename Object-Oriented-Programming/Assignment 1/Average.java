import java.util.Scanner;
public class Average {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter a series of numbers. Enter a negative number to quit.");
		double avg = 0;
		int total = 0;
		while (true) {
			double input = s.nextDouble();
			if (input >= 0) {
				avg += input;
				total++;
			}
			else break;
		}
		avg /= total;
		System.out.println("You entered "+total+" numbers averaging "+avg);
	}
}
		