# Assignment 2
## Factorial.java , FibTest.java , Ramanujan.java, FunctionTest.java , Poly.java , Function.java , RamanujanBig.java , SinFunc.java , CosFunc.java , PolyFunc.java
### Factorial - Factorial.java
Contains a method ***public static long calculate(long n)*** which recursively calculates n!, where 0! = 1 and n! = n(n − 1)!. Prints out an error 
and exits if n < 0 or n > 20, since factorial is not defined for negative numbers and will overflow Java’s long variable with larger numbers.
```
long result1 = Factorial.calculate(0);
System.out.println("Factorial.calculate(0) returned " + result1 + ". Test passed!");

long result2 = Factorial.calculate(5);
System.out.println("Factorial.calculate(5) returned " + result2 + ". Test passed!");
```
Output:
```
Factorial.calculate(0) returned 1. Test passed!
Factorial.calculate(5) returned 120. Test passed!
```
### Fibonacci revisted - FibTest.java
Contains two methods for calculating Fibonacci nth term: ***public static int fibIter(int n)*** and 
***public static int fibRecur(int n)*** which use an iterative and recursive approach, respectively.
Will also test both methods and prints out the time it takes to execute each method for the input 40.
```
int result1 = FibTest.fibIter(7);
System.out.println("FibTest.fibIter(7) returned " + result1 + ". Test passed!");

int result2 = FibTest.fibRecur(7);
System.out.println("FibTest.fibRecur(7) returned " + result2 + ". Test passed!");
```
Output:
```
FibTest.fibIter(7) returned 13. Test passed!
FibTest.fibRecur(7) returned 13. Test passed!
ms to execute FibTest.fibIter(40): <Varies by device>
ms to execute FibTest.fibRecur(40): <Less than fibIter(40)>
```
### π revisited - Ramanujan.java
Ramanujan's approximation for 1/π converges ***much*** faster than the Gregory series. This program 
takes a number n specified by the user on the command line and 
calculates π using the first n terms of the Ramanujan series. Prints this approximate value of π, 
as well as the percentage error between this value and the one provided by Java in the constant Math.PI.

To run the program, use the following command in your terminal:
```
$ java Ramanujan 1
```
The output will be:
```
Pi according to Ramanujan series: 3.1415926535897936
This is error bound from Java's value by 2.842170943040401E-14 percent
```
### Ramanujan series represented w/ BigDecimal class - RamanujanBig.java
Interestingly, this method creatively makes use of my Polynomial and Root class to retrieve the necessary approximation of sqrt(2) constant in the
Ramanujan series. By using BigDecimal class, we can estimate π to a larger number of digits.

To run the program, use the following command in your terminal:
```
java RamanujanBig 3
```
The output will be:
```
Pi according to Ramanujan series: 3.1415926535897932384626433832795028841971693993751
```
### Root finding - FunctionTest.java
Contains a method ***public static double findRoot(double a, double b, double epsilon)*** 
which uses the bisection method to find the root of the Math.sin() function between two given values with a given level of tolerance.

Tests the root of the particular function sin(x) that falls between 3 and 4, to within 0.00000001.
```
double result = FunctionTest.findRoot(3, 4, 0.00000001);
System.out.println("FunctionTest.findRoot(3, 4, 0.00000001) returned " + result + ".");
```
Output:
```
FunctionTest.findRoot(3, 4, 0.00000001) returned 3.1415926519626075.
```
### Polynomial - Poly.java
***Extremely*** useful throughout many classes. 
Contains a Java class Poly that represents polynomials 
(i.e., functions of the form ax^n + bx^(n-1) + ... + cx^2 + dx + e).

***Poly(int[] coefficients)***: Constructor for an array of coefficients where c[n] is the coefficient of x^n. In other words, 
polynomial 2x^5 + 3x^4 - 8x^2 + 4 would be represented by the array ***[4, 0, -8, 0, 3, 2]***.

***int degree()***: Returns the power of the highest non-zero term.

***String toString()***: Returns a string representation of the polynomial using x as the variable, 
arranged in decreasing order of exponent and printing nothing for terms with a coefficient of zero. For example, 
the Poly represented by the array ***[4, 0, -8, 0, 3, 2]*** should return the string: ***2x^5+3x^4-8x^2+4***.

***Poly add(Poly a)***: Constructs and returns a new Poly object with a new coefficient array 
created by adding the coefficients of the current object and the Poly object passed as an argument to the ***add()*** function.

***double evaluate(double x)***: Returns the value of the function at the point x.
Additionally, the project contains a main method to test each of the methods.

### Inheritance implementation - Function.java, SinFunc.java, CosFunc.java, PolyFunc.java, 
In this part of the project, I extend the functionality of the Function class. The Function class supports an abstract method:
```
public abstract double evaluate(double x)
```
***FunctionTest.findRoot()*** method is refactored to belong to the Function class. 

***evaluate()*** method replaces the use of ***Math.sin()*** in ***findRoot()*** method. A new class SinFunc is created, which extends Function and implements ***evaluate()*** using ***Math.sin()***. 
Similarly, CosFunc is created, which implements ***Math.cos()***. Lastly, I create a new class PolyFunc which extends Function and implements the 
***evaluate()*** method for polynomials.

The implemented abstract method ***public abstract double evaluate(double x)*** evaluates a given function at a particular point x.
The SinFunc, CosFunc, and PolyFunc classes each extend Function and implement their respective evaluate() method. These classes contain no additional methods.
 
In ***Function.main()***, I verify that the root of sin(x) between 3 and 4 is the same as it was before, and find the root of cos(x) between 1 and 3. 
I also find the positive root (x > 0) of x^2 - 3 and x^2 - x - 2.
```
Function sin = new SinFunc();
System.out.println(sin.findRoot(3, 4, 0.00000001));
		
Function cos = new CosFunc();
System.out.println(cos.findRoot(1, 3, 0.00000001));
		
int[] coef1 = {-3, 0, 1};
int[] coef2 = {-2, -1, 1};
Function polynomial1 = new PolyFunc(coef1);
Function polynomial2 = new PolyFunc(coef2);
		
System.out.println(polynomial1.findRoot(1,2, 0.000000001));
System.out.println(polynomial2.findRoot(1,3, 0.000000001));
```
Yields:
```
3.1415926590561867
1.5707963332533836
1.7320508072152734
2.0000000009313226
```
### Convenient Utilities - ConvenciencyMethods.txt
Contains some discarded convenient methods used to complete this assignment. Class not runnable, but methods inside can be implemented in other projects.

***public int[] getDegreesArray(int[] c)*** method returns the array of degrees to link to their corresponding coefficients for the Poly class.

***public HashMap<Integer,Integer> getDegreesMap(int[] c)*** method takes an array as an argument and returns a HashMap of coefficients linked to 
the value of their degrees.

***public int[] reverse(int[] arr, int arr_length)*** simple but powerful method to reverse an array. Extremely useful when dealing with polynomials.
