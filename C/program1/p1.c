/* Program 1: Command Line Arguments
// CS2433 Fall 2023
// Dr. Churchill
// Due: Saturday, September 2, 2023, 11:59 PM.
// Submitted:
// Drake Geeteh. CSX ID: dgeeteh
// Processes command line arguments
*/
#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <stdlib.h>
int main(int argc, char *argv[]) {

	printf("Program name: %s\n",argv[0]);

	int sum = 0;

	if (argc != 1) {
		if (strcmp(argv[1], "--help") == 0) {
			printf("Program processes command line arguments\n");
			printf("Usage: [--help] <Integer> <Integer> ...\n");
		}
	}

	for (int i=1; i<argc; i++) {
		if (isdigit(argv[i][0]) || (argv[i][0] == '-' && isdigit(argv[i][1]))) {
			int num = atoi(argv[i]);
			sum += num;
			printf("Current Sum: %d\n", sum);
		}
		else {
			printf("%s\n", argv[i]);
		}
	}
	printf("The sum is: %d\n",sum);
	return 0;
}

