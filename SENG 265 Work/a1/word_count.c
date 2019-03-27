
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

int alph(const void* one, const void* two){		//wrapper for qsort
	return strcmp(one, two);
}

int main(int argc, char* argv[]){
   
   char input[750][35];	//Where the text from file gets dumped as strings
   FILE* infile;
   int n = 0;
   int sortCheck = 0;	//on off for sort
   int dispCheck = 0;	//on off for print words
   int numWords = 0;	//num of words read
   
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
			   while(fscanf(infile, "%34[a-zA-Z]s ", input[n]) == 1){	//Reads characters up to spceial character/space  
				   fscanf(infile, "%*[^a-zA-Z]");	//eats the next special character
				   numWords++;
				   n++;
			   }
			   fclose(infile);
		   }
	   }
	   else if(i == argc-1 && infile == NULL){
		   printf("Please enter an input file ");
	   }
   }
   	
	for(int b = 0; b < numWords; b++){
		for(int y = 0;y < 35; y++){
			input[b][y] = tolower(input[b][y]);
		}
	}

	qsort(input, numWords, 35, alph);	//Sorts array so it will be output in alphabetical order
   
	int count[35];		//Holds the amount of strings at each length

	char words[35][750][35];	//Where the array split apart into length will go
	for(int k = 1; k < 35; k++){	//Outputing results
		for(int l = 0; l < numWords; l++){	//Finds length of character in input and puts into words
			if(strlen(input[l]) == k){
				if(dispCheck == 1){		//only needed when displaying words
					strncpy(words[k][count[k]], input[l], 35);
				}
				count[k]++;
			}
		}
		if(sortCheck == 0){		//only uses this when sorting not needed
			if(count[k] > 0){	//only outputs result for length that are represented
				printf("Count[0%d]=0%d; ", k, count[k]);
				if(dispCheck == 1){		//Code for displaying words
					printf("(words: ");
					if(count[k]-1 >0){	//Prevents and before single string				
						for(int u = 0; u < count[k]-1; u++){	//goes through each word to display
							if(strcmp(words[k][u],words[k][u+1]) == 0){	//if a word has a duplicate remove it
								strcpy(words[k][u],"");		//removing dupe
							}else{
								printf("\"%s\", ", words[k][u]);
							}
						}
						printf("and \"%s\")\n", words[k][count[k]-1]);	//ending display
					}
					else{
						printf("\"%s\")\n", words[k][count[k]-1]);
					}
				}else{
					printf("\n");	//prints new line when not displaying words
				}
			}	
		}
	}
	if(sortCheck == 1){		//Code for displaying results when sorted
		int high;	//Highest current letter count 
		int next;	//Next length of strings to display
		for(int h = 0; h < numWords; h++){
			high = 0;		//resets highest letter count after use
			for(int g = 0; g < 35; g++){
				if(high < count[g]){
					high = count[g];
					next = g;
				}
			}
			if(count[next] > 0){	//Only displays represented lengths
				printf("Count[0%d]=0%d; ", next, count[next]);
				if(dispCheck == 1){		//Prints words
					printf("(words: ");
					if(count[next]-1 >0){	//Prevents and before single string
						for(int u = 0; u < count[next]-1; u++){	//goes through words in each length
							if(strcmp(words[next][u],words[next][u+1]) == 0){	//removes duplicates
								strcpy(words[next][u],"");
							}
							else{
								printf("\"%s\", ", words[next][u]);
							}
						}
					
						printf("and \"%s\")\n", words[next][count[next]-1]);
					}else{
						printf("\"%s\")\n", words[next][count[next]-1]);
					}
				}else{
					printf("\n");
				}
			}
			count[next] = 0;	//sets the highest count to zero to display the next highest
		}
	}
	return(0);
}
