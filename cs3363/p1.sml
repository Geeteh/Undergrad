(*
Programming Assignment 1: Functions as Parameters
CS3363 Fall 2024
Richard Churchill
Due: Saturday, September 7, 2024, 11:59PM
Submitted: Saturday, September 7, 2024, 9:10PM
Drake Geeteh. csx: dgeeteh
p1.sml contains 'iinsert' && 'iinsort' functions which recursively inserts some real number
into a list based on its comparator function. op< && op<= comparators will
sort the list in ascending order. op> && op>= comparators will sort the list
in descending order. 'iinsert' will sort x value to its appropriate position for sorted lists.
The algorithm compares the head element and recurses over the tail
until its correct position is found.
*)

fun iinsert([], x, comp) = [x]
  | iinsert(hd::tl, x, comp) =
      if comp(x, hd)
      then x::hd::tl
      else hd::iinsert(tl, x, comp);

fun iinsort([], comp) = []
  | iinsort(x::xs, comp) = iinsert(iinsort(xs, comp), x, comp);
