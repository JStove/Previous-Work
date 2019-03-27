import re
import sys

def createtxt(file):
    try:
        input = open(file, "r")
    except:
        print("Failed to find text file")
        sys.exit()
    temp = input.readlines()
    try:
        words = temp[0].split()
    except:
        print("Failed to read text file")
        sys.exit()
    words = [word.replace(word, re.sub(r'[^a-zA-Z ]', '', word)) for word in words]
    words.sort()
    input.close()
    return words


pflag = False
sflag = False
path = ''
for c in range(len(sys.argv)):
        if sys.argv[c] == '--sort':
            sflag = True
        elif sys.argv[c] == '--print-words':
            pflag = True
        elif sys.argv[c] == '--infile':
            path = sys.argv[c+1]

if path == '':
    print("Please input a file")
    sys.exit()

words = createtxt(path)
if words == '':
    print("Failed to read text file")
    sys.exit()
    
maxlen = 0
for word in words:
    if maxlen < len(word):
        maxlen = len(word)

numlen = [0]*(maxlen+1)
for a in range(0, maxlen+1):
    for w in words:
        if a == len(w):
            numlen[a] += 1            

order = [0]*(maxlen+1)
for p in range(0, len(numlen)):
    largest = 0
    for d in range(len(numlen)):
        if numlen[d] > numlen[largest]:
            largest = d
    order[p] = largest
    numlen[largest] = 0


if sflag:
    for i in order:
        count = 0
        for w in words:
            if i == len(w):
                count += 1
        if count > 0:
            print("Count["+ str(i).zfill(2) + "]=[" + str(count).zfill(2) + "]; ", end='')
            if pflag:
                print("(words: ", end='')
                printed = 0
                for c in range(len(words)):
                     if i == len(words[c]) and count == 1:
                        print("\"" + words[c] + "\")")
                    
                     elif i == len(words[c]) and printed != count-1:
                        printed += 1
                        print("\"" + words[c] + "\"", end='')
                        if printed == count-2:
                            print(", ", end='')
                     elif i == len(words[c]) and printed == count-1 and count != 1:
                        print(" and \"" + words[c] + "\")")
            else:
                print("")
else:
    for i in range(maxlen+1, 1, -1):
        count = 0
        for w in words:
            if i == len(w):
                count += 1
        if count > 0:
            print("Count["+ str(i).zfill(2) + "]=[" + str(count).zfill(2) + "]; ", end='')
            if pflag:
                print("(words: ", end='')
                printed = 0
                for c in range(len(words)):
                     if i == len(words[c]) and count == 1:
                        print("\"" + words[c] + "\")")
                    
                     elif i == len(words[c]) and printed != count-1:
                        printed += 1
                        print("\"" + words[c] + "\"", end='')
                        if printed == count-2:
                            print(", ", end='')
                     elif i == len(words[c]) and printed == count-1 and count != 1:
                        print(" and \"" + words[c] + "\")")
            else:
                print("")
                
                
               
                

            
        
    
