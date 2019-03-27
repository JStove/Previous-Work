#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

struct word{
    char* str;
    struct word *next;
};

struct word *head = NULL;
struct word *curr = NULL;

struct word* create_list(char* str){

    struct word *ptr = (struct word*)malloc(sizeof(struct word));
    if(NULL == ptr){
        printf("\n Node creation failed \n");
        return NULL;
    }
	char* temp = strdup(str);
    ptr->str = temp;
    ptr->next = NULL;
    head = curr = ptr;
	return ptr;
	
}

struct word* add(char* str){
    if(NULL == head){
        return (create_list(str));
    }
	
	struct word *test = head;
		while (test != NULL){
			if(strcmp(test->str, str) == 0){
				return 0;
			}
			test = test->next;
		}

    struct word *ptr = (struct word*)malloc(sizeof(struct word));
    if(NULL == ptr)
    {
        printf("\n Node creation failed \n");
        return NULL;
    }
    ptr->str = str;
    ptr->next = NULL;

    curr->next = ptr;
	curr = ptr;
    
    return ptr;
}

char* lowercase(char* input){
	
	int length = strlen(input);
	char* lower = (char*)malloc((length+1));
	lower[length] = 0; 
	for(int i = 0; i < length; i++){
		lower[i] = tolower(input[i]);
	}
	return lower;
}

void lengthp(int size, int freq){
	printf(" (words: ");
	struct word *print = head;
	while (print != NULL){
		if(strlen(print->str) == size){
			if(freq > 1){
				printf("\"%s\", ", print->str);		
				freq--;
			}else{
					printf("\"%s\")", print->str);
			}
		}
		print = print->next;
	}
}

void noSort(int large, int check){
	for(int i = 1; i <= large; i++){
		struct word *test = head;
		int freq = 0;
		while (test != NULL){
			if(strlen(test->str) == i){
				freq++;
			}
			test = test->next;
		}
		if(freq > 0){
			printf("Count[%02d]=%02d;", i, freq);
			if(check==1){
				lengthp(i, freq);
			}
			printf("\n");
		}
	}
}

void sorted(int large, int check){
	int donehigh[large];
	for(int k = 1; k <= large; k++){
		int highf = 0;
		int highfi = 0;
		
		for(int i = 1; i <= large; i++){
			struct word *test = head;
			int freq = 0;
			while (test != NULL){
				if(strlen(test->str) == i){
					freq++;
				}
				test = test->next;
			}
			int flag = 1;
			for(int a = 0; a < large; a++){
				if(donehigh[a] == i){
					flag = 0;
				}
			}
			
			if(flag == 1){
				if(highf < freq){
					highfi = i;
					highf = freq; 
					donehigh[k] = i;
				}
			}
			
		}
		if(highf > 0){
			printf("Count[%02d]=%02d;", highfi, highf);
			if(check==1){
				lengthp(highfi, highf);
			}
			printf("\n");
		}
	}
}

int main(int argc, char* argv[]){

   char *input;	//Where the text from file gets dumped as strings
   FILE* infile;
   int n = 0;
   int large = 0;
	int dispCheck = 0;	//on off for print words
	int sortCheck = 0;	//on off for sort
   
   for(int p = 0; p < argc; p++){			//Checks args for sort+print
	   if(strcmp("--sort", argv[p]) == 0){
			sortCheck = 1;
	   }
	   else if(strcmp("--print-words", argv[p]) == 0){
		   dispCheck = 1;
	   }
   }
   for(int i = 0; i < argc; i++){		//Creates char array of words from text file
	   if(strcmp("--infile", argv[i]) == 0){	//Checks for input
		   infile = fopen(argv[i+1], "r");
		   
		   if(infile == NULL){
				printf("Did not find file \n");
		   }
		   else{
			   while(fscanf(infile, "%34[a-zA-Z]s ", input) == 1){	//Reads characters up to spceial character/space  
					fscanf(infile, "%*[^a-zA-Z]");	//eats the next special character
					add(lowercase(input));
					n++;
					if(large < strlen(input)){
						large = strlen(input);
					}
			   }
			   fclose(infile);
		   }
	   }
	   else if(i == argc-1 && infile == NULL){
		   printf("Please enter an input file ");
	   }
   }
	
	if(sortCheck == 0){
		noSort(large, dispCheck);
	}else{
		sorted(large, dispCheck);
	}
	return(0);
}