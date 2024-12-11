; p3.sc
; course: CS3363, Fall 2024, Professor Churchill
; assignment: Scheme Insertion Sort Function -- Programming assignment 3
; author: Drake Geeteh
; due: October 5, 2024, 11:59 PM
; submitted: October 5, 2024 @ 11:10 PM
; build instructions: Load the file using (load "p3.sc") in mit-scheme
; directions: Call the function sins and give a sample list of real numbers to be sorted as the function parameter

(define (insert x sorted-list)
  (cond
    ((null? sorted-list) (list x))
    ((<= x (car sorted-list)) (cons x sorted-list))
    (else (cons (car sorted-list) (insert x (cdr sorted-list))))))

(define (sins lst)
  (cond
    ((null? lst) '())
    (else (insert (car lst) (sins (cdr lst))))))
