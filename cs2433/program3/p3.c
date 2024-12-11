/*
// Assignment 3: stdin - Reading and Processing RPN
// CS2433 Fall 2023
// Dr. Churchill
// Due: Saturday, September 23, 2023, 11:59PM
// Submitted: Friday, September 22, 2023, 11:47PM
// Drake Geeteh, csx user ID: dgeeteh
// Program accepts floats and operands (+, -, *, /). Operands are operable on
// the top two elements on the stack. Terminates by stack overflow/underflow or
// until `#stop` is encountered. Returns the linked list after user's operations
// are complete.
*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct node {
	float val;
	struct node *next;
} tnode;

tnode *myFree = NULL;
tnode *stack = NULL;

void push(float a) {
	tnode *pnode;
	if (myFree == NULL) {
        	pnode = (tnode *)malloc(sizeof(tnode));
    	}
	else {
        	pnode = myFree;
        	myFree = pnode->next;
    	}
	pnode->val = a;
	pnode->next = stack;
	stack = pnode;
	return;
}

float pop() {
	tnode *pnode;
    	if (stack == NULL) {
        	printf("Stack Underflow Error!\n");
        	exit(1);
    	}
	pnode = stack;
	stack = pnode->next;
	pnode->next = myFree;
	myFree = pnode;
	return pnode->val;
}

int main(int argc, char *argv) {
	char input[100];

	while (1) {
		scanf("%s", input);
        	switch (input[0]) {
            		case '#':
                	if (strcmp(input, "#stop") == 0) {
                    		if (stack == NULL) {
                        		printf("Empty Stack Error!\n");
                        		exit(1);
                    		} else {
                        		while (stack != NULL) {
                            			printf("%.2f  ", pop());
                       			}
                        		printf("\n");
                       			exit(0);
                    		}
                	}
                	break;

            	case '+':
            	case '-':
            	case '*':
            	case '/':
                	if (stack == NULL) {
                	    printf("Stack Underflow Error!\n");
                	    exit(1);
                	}
                	float a = pop();
                	if (stack == NULL) {
                	    printf("Stack Underflow Error!\n");
                	    exit(1);
                	}
                	float b = pop();
                	switch (input[0]) {
                	    case '+':
                	        push(b + a);
                	        break;
                	    case '-':
                	        push(b - a);
                	        break;
                	    case '*':
                	        push(b * a);
                	        break;
                	    case '/':
                	        if (a == 0) {
                	            printf("Division by zero error!\n");
                	            exit(1);
                	        }
                	        push(b / a);
                	        break;
                	}
                	break;

            	default:
                	float val;
                	sscanf(input, "%f", &val);
                	push(val);
                	break;
        	}
    	}

    	return 0;

}
