#include <stdio.h>

int main() {

	while (1) {
	int *p;
	int temp = 5;
	p = &temp;
	printf("%d\n", temp);
	printf("%x\n", &temp);
	printf("%x\n", p);
	printf("%x\n", &p);
	printf("%d\n", *p);
	printf("\n");
	}

	return 0;
}
