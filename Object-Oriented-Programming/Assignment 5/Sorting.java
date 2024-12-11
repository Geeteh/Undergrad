/*
/  Bubble sort has a worst-case time complexity of O(n^2), and merge sort has a worst-case time complexity of O(nlogn).
/  This means that bubble sort will take much longer to sort arrays compared to merge sort.
/  As 'n' grows larger (as seen by my program), the difference between the bubble sort and merge sort algorithms becomes increasingly more pronounced.
/  When the program is ran on my laptop, merge sort is able to sort an array of 1,000,000 ints within roughly 40 ms of the time it takes bubble sort to sort an array of 10,000 ints.
*/

import java.util.*;

public class Sorting {
	
	public static double[] randomArray(int n) {
		Random random = new Random();
		double[] array = new double[n];
		for (int i = 0; i < n; i++) {
			array[i] = random.nextDouble();
		}
		return array;
	}
	
	public static void show_bubble_sort(double[] array) {
		System.out.println();
		for (int i = 0; i < array.length; i++) {
			System.out.print("[ ");
			for (int k = 0; k < array.length; k++) {
				System.out.print(array[k]);
				if (k < array.length - 1) {
					System.out.print(", ");
				}
			}
			
				for (int j = 0; j < array.length - i - 1; j++) {
					if (array[j] > array[j+1]) {
						double temp = array[j];
						array[j] = array[j+1];
						array[j+1] = temp;
					}
				}
			System.out.println(" ]");
		}
	}
	
	public static void show_merge_sort(double[] array) {
		System.out.println();
		System.out.print("Inital: [ ");
		for (int u = 0; u < array.length; u++) {
			System.out.print(array[u]);
			if (u < array.length - 1) {
				System.out.print(", ");
			}
		}
		System.out.println(" ]");
		
		if (array.length > 1) {
			int half = array.length / 2;
			double[] leftHalf = Arrays.copyOfRange(array, 0, half);
			double[] rightHalf = Arrays.copyOfRange(array, half, array.length);
			
			runtime_merge_sort(leftHalf, System.currentTimeMillis(), 0);
			runtime_merge_sort(rightHalf, System.currentTimeMillis(), 0);
			
			int i = 0;
			int j = 0;
			int k = 0;
			
			while (i < leftHalf.length && j < rightHalf.length) {
				if (leftHalf[i] < rightHalf[j]) array[k++] = leftHalf[i++];
				else array[k++] = rightHalf[j++];
			}
			while (i < leftHalf.length) array[k++] = leftHalf[i++];
			
			while (j < rightHalf.length) array[k++] = rightHalf[j++];
			
			System.out.print("Final: [ ");
			for (int u = 0; u < array.length; u++) {
				System.out.print(array[u]);
				if (u < array.length - 1) {
					System.out.print(", ");
				}
			}
			System.out.println(" ]");
			
		}
	}
	
	public static double runtime_bubble_sort(double[] array, double startTime) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length - i - 1; j++) {
				if (array[j] > array[j+1]) {
					double temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
				}
				if (System.currentTimeMillis() - startTime > 20000) {
					System.out.println("Bubble sort timed out at 20 seconds while sorting an array of length " + array.length);
					return -1;
				}
			}
		}
		return System.currentTimeMillis() - startTime;
	}
	
	public static double runtime_merge_sort(double[] array, double startTime, int n) {
		if (array.length > 1) {
			int half = array.length / 2;
			double[] leftHalf = Arrays.copyOfRange(array, 0, half);
			double[] rightHalf = Arrays.copyOfRange(array, half, array.length);
			
			double leftTime = runtime_merge_sort(leftHalf, startTime, n);
			if (leftTime < 0) return -1;
			double rightTime = runtime_merge_sort(rightHalf, startTime, n);
			if (rightTime < 0) return -1;
			
			
			int i = 0;
			int j = 0;
			int k = 0;
			
			while (i < leftHalf.length && j < rightHalf.length) {
				if (System.currentTimeMillis() - startTime > 20000) {
					System.out.println("Merge sort timed out at 20 seconds while sorting an array of length " + n);
					return -1;
				}
				if (leftHalf[i] < rightHalf[j]) array[k++] = leftHalf[i++];
				else array[k++] = rightHalf[j++];
			}
			while (i < leftHalf.length) {
				if (System.currentTimeMillis() - startTime > 20000) {
					System.out.println("Merge sort timed out at 20 seconds while sorting an array of length " + n);
					return -1;
				}
				array[k++] = leftHalf[i++];
			}
			
			while (j < rightHalf.length) {
				if (System.currentTimeMillis() - startTime > 20000) {
					System.out.println("Merge sort timed out at 20 seconds while sorting an array of length " + n);
					return -1;
				}
				array[k++] = rightHalf[j++];
			}
		}
		return System.currentTimeMillis() - startTime;
	}

	
	
	public static void main(String[] args) {
		/*
		double[] arr1 = randomArray(5);
		show_bubble_sort(arr1);
		show_merge_sort(arr1);
		*/
		int n = 10;
		double mergeSortTime = 0;
		double bubbleSortTime = 0;
		boolean bubbleFlag = true;
		boolean mergeFlag = true;
		while (bubbleFlag || mergeFlag) {
			try {
				double[] array = randomArray(n);
				
				if (bubbleFlag != false) bubbleSortTime = runtime_bubble_sort(array, System.currentTimeMillis());
				if (bubbleSortTime != -1) System.out.println("Bubble sort sorted array of length " + n + " in: " + bubbleSortTime + " ms");
				else bubbleFlag = false;
				
				if (mergeFlag != false) mergeSortTime = runtime_merge_sort(array, System.currentTimeMillis(), n);
				if (mergeSortTime != -1) System.out.println("Merge sort sorted array of length " + n + " in: " + mergeSortTime + " ms");
				else mergeFlag = false;
				
				if (bubbleFlag == false && mergeFlag == false) break;
				
				n *= 10;
				System.out.println();
			}
			catch (OutOfMemoryError e) {
				System.out.println("Cannot allocate memory");
				e.printStackTrace();
				}
				
		}
	}
	
		
		
			

	
	
}
