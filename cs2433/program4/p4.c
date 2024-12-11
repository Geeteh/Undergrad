/*
// Program 4: Extending Processing of RPN, plus Variables and Constants
// CS2433 Fall 2023
// Dr. Churchill
// Due: Saturday, October 7, 2023, 11:59 PM
// Submitted: Saturday, October 7, 2023, 8:02 PM
// Drake Geeteh. CSX ID: dgeeteh
// Code extends functionality of program 3. Additional operands have been added:
// ^ (power), ' (reciprocate), ~ (negation), & (dup),
// % (swap), ! (named constant), $ (named variable).
// `#stop` will terminate the program
*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

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

typedef struct cvNode {
	int type;
	float value;
	char *name;
	struct cvNode *next;
} tcvNode;

tcvNode *constVarList = NULL;

float findConstVarValue(const char *name) {
	tcvNode *current = constVarList;
	while (current != NULL) {
		if (strcmp(current->name, name) == 0) {
			return current->value;
		}
	}
	current = current->next;
	printf("Error: Undefined constant or variable '%s'\n", name);
	exit(1);
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

		case '^':
			if (stack == NULL) {
				printf("Stack Underflow Error!\n");
				exit(1);
			}
			float y = pop();
			if (stack == NULL) {
				printf("Stack Underflow Error!\n");
				exit(1);
			}
			float x = pop();
			push(pow(x, y));
			break;

		case '&':
			if (stack == NULL) {
				printf("Stack Underflow Error!\n");
				exit(1);
			}
			x=pop();
			push(x);
			push(x);
			break;
		case '~':
			if (stack == NULL) {
				printf("Stack Underflow Error!\n");
				exit(1);
			}
			x=pop();
			push(-x);
			break;
		case '\'':
			if (stack == NULL) {
				printf("Stack Underflow Error!\n");
				exit(1);
			}
			x=pop();
			if (x==0) {
				printf("Division by zero error!\n");
				exit(1);
			}
			push(1.0/x);
			break;
		case '%':
			if (stack == NULL || stack->next == NULL) {
				printf("Stack Underflow Error!\n");
				exit(1);
			}
			x=pop();
			y=pop();
			push(x);
			push(y);
			break;
		case '!':

                	char *name = input + 1;
                	float val = pop();
                	tcvNode *node = constVarList;
                	while (node != NULL) {
                	    if (strcmp(node->name, name) == 0) {
                	        printf("Error: Name '%s' is already defined as a constant\n", name);
                	        exit(1);
                	    }
                	    node = node->next;
                	}
                	tcvNode *newNode = (tcvNode *)malloc(sizeof(tcvNode));
                	newNode->type = 0; // 0 for constant
                	newNode->value = val;
                	newNode->name = strdup(name);
                	newNode->next = constVarList;
                	constVarList = newNode;
                	break;

		case '$':
                	name = input + 1;
                	val = pop();
                	node = constVarList;
                	while (node != NULL) {
                	    if (strcmp(node->name, name) == 0) {
                	        node->value = val;
                	        break;
                	    }
                	    node = node->next;
                	}
                	if (node == NULL) {
                	    newNode = (tcvNode *)malloc(sizeof(tcvNode));
                	    newNode->type = 1; // 1 for variable
                	    newNode->value = val;
                	    newNode->name = strdup(name);
                	    newNode->next = constVarList;
                	    constVarList = newNode;
                	}
                	break;            	
		default:
			if (sscanf(input, "%f", &val) == 1) {
				push(val);
			}
			else push(findConstVarValue(input));
			break;
		}
    	}

    	return 0;

}
