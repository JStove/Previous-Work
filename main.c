#include <stdio.h>

struct data {	//single point in list
	int num;
	struct data* next;
	struct data* prev;
};

void push(int newnum, struct data** head_ref) {
	struct data* toAdd = (struct data*)malloc(sizeof(struct data));		//allocates memory for data struct

	toAdd->num = newnum;
	toAdd->next = (*head_ref);
	toAdd->prev = NULL;
	if ((*head_ref) != NULL) {
		(*head_ref)->prev = toAdd;
	}

	(*head_ref) = toAdd;
}

void append(int newnum, struct data** head_ref) {
	struct data* toAdd = (struct data*)malloc(sizeof(struct data));

	struct data* end = *head_ref;

	toAdd->num = newnum;
	toAdd->next = NULL;
	
	if ((*head_ref) == NULL) {
		toAdd->prev = NULL;
		*head_ref = toAdd;
		return;
	}

	while (end->next != NULL) {
		end = end->next;
	}

	end->next = toAdd;
	toAdd->prev = end;
}

void printList(struct data* node) {
	struct data* end;

	while (node != NULL) {
		printf("%d", node->num);
		end = node;
		node = node->next;
	}
}

int main(){
	struct data* current = NULL;

	push(2, &current);
	append(3, &current);
	append(5, &current);
	printList(current);

	return 0;
}