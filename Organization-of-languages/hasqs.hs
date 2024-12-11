-- Drake Geeteh, csxid: dgeeteh
-- Programming Assignment 2
-- Due: September 21, Saturday 11:59pm
-- Submitted: September 19, Thursday, 9:20pm
-- Prof Richard Churchill, CS3363, Fall 2024
-- Reference to: http://www.haskell.org/ghc/docs/latest/html/users_guide/

-- Description of algorithm:
-- Quicksort "divide-and-conquer" sorting algorithm which selects the first (or random)
-- element (which we call the pivot) from the list, then recursively partition elements
-- into 2 sublists {elements <= pivot} & {elements > pivot} and recombine them.

-- Instructions for interpreted execution:
-- Run commands:
-- ghci hasqs.hs
-- hasqs [<Integer>, <Integer>, ...]

-- Instructions for compiled execution:
-- Run commands:
-- ghc hasqs.hs
-- ./hasqs

hasqs :: (Ord a) => [a] -> [a]
hasqs [] = [] -- BC
hasqs (x:xs) =
        hasqs [y | y <- xs, y <= x]
        ++ [x] ++
        hasqs [y | y <- xs, y > x]

-- Sample input/output for the compiled execution
main :: IO ()
main = do
    let result = hasqs [5, 1, 9, 4, 6]
    print result
