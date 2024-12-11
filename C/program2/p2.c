/* Program 2: Command Line Arguments (linked list)
// CS2433 Fall 2023
// Dr. Churchill
// Due: September 16, 2023 at 11:59 PM.
// Submitted: September 16, 2023 at 3:18 PM.
// Drake Geeteh. User ID: dgeeteh
// Program reads FPVs and strings from cmd line until "#stop" is encountered,
// and builds a linked list out of the provided values. Program output prints
// each element of the constructed list.
*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define TRUE -1
#define FALSE 0
#define STRMAX 128
#define STRSAV 8

#define VAL 0
#define NAM 1

union content {
	float fv;
	char tx[STRMAX];
};

typedef struct node {
	int discrim;
	union content c;
	struct node *next;
} tnode;

struct node *base = NULL;

int main(int argc, char *argv[]) {
	int loopCtrl = TRUE;
	float fpv;
	char intext[STRMAX];
	struct node *pnode;

	while (loopCtrl == TRUE) {
		scanf("%s", intext);
		if (strcmp(intext, "#stop") == 0) {
			loopCtrl = FALSE;
			continue;
		}

		pnode = malloc(sizeof(tnode));

		if (pnode == NULL) {
			printf("System error: Could not allocate node!\n");
			exit(-1);
		}
		if (sscanf(intext, "%f", &fpv) == 1) {
			pnode->discrim = VAL;
			pnode->c.fv = fpv;
		}
		else {
			pnode->discrim = NAM;
			strncpy(pnode->c.tx, intext, STRSAV);
			pnode->c.tx[STRSAV-1] = '\0';
		}
		pnode -> next = base;
		base = pnode;
	}

	printf("\nGenerated Output:\n");

	pnode=base;
	while (pnode != NULL) {
		if (pnode->discrim == VAL) {
			printf("%f\n", pnode->c.fv);
		}
		pnode = pnode->next;
	}

	pnode = base;
	while (pnode != NULL) {
		if (pnode->discrim == NAM) {
			printf("%.7s\n", pnode->c.tx);
		}
		pnode = pnode->next;
	}

	pnode = base;
	while (pnode!=NULL) {
		struct node *temp = pnode;
		pnode = pnode->next;
		free(temp);
	}

	return 0;
}
