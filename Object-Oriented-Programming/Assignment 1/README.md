# Assignment 1
## Greeting.java , Average.java , Fib.java , Gregory.java
### Not Hello World - Greeting.java
It can say anything, except for “Hello, world!”

To run the program, use the following command in your terminal:
```
$ java Greeting
```
The output should be:
```
¡Hola, mundo!
```
### Statistics - Average.java
Allows the user to continue to enter numbers until they respond with a negative number. Prints out how many numbers the user entered (not including the negative one), and the average of those numbers.

To run the program, use the following command in your terminal:
```
$ java Average
Enter a series of numbers. Enter a negative number to quit.
1
2
4.5
3
5
-1
```
The output should be:
```
You entered 5 numbers averaging 3.1.
```
### Fibonacci numbers - Fib.java
The Fibonacci sequence is a famous mathematical sequence where each successive term is the sum of the two preceding ones. Using the Scanner object, this program allows a user to enter a number n as a command-line argument, and will print out the nth Fibonacci term.

To run the program, use the following command in your terminal:
```
$ java Fib 4
```
The output should be:
```
3
```
### Investigations into π - Gregory.java
The Gregory series is a series converging to a π/4. This program takes a number n specified by the user (via the command line) and calculates π using the first n terms of the Gregory series, prints the approximated value of π, as well as the percentage error between this value and the one provided by Java in the constant Math.PI.

To run the program, use the following command in your terminal:
```
$ java Gregory 10
```
The output should be:
```
Pi according to Gregory series: 3.0418396189294032
This differs from Java’s value by 3.175237710923643 percent.
```
