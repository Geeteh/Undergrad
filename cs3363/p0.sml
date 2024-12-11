(*
Program 0
CS3363 Fall 2024
Churchill
Due: Saturday, Aug 24, 11:59PM
Submitted: Saturday, Aug 24, 12:15AM
Drake Geeteh. dgeeteh

Function is a recursive def of polynomial
which takes a list of coefs and an x value as parameters
and return their evaluation 
*)

fun epoly ([], _) = 0.0
  | epoly (coeff::coeffs, x) = coeff + x * epoly (coeffs, x)
