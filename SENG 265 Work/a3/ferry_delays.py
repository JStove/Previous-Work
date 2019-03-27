import re
cond = ''
while cond != 'q':
    cond = input("Enter 's' for Swartz Bay, 't' for Tsawwassen and 'q' for quit:")
    if cond != 'q':
        mon = input("Enter the number of the month you want:")
    try:
        input = open('bc_ferries_route1_June2017.csv', "r")
    except:
        print("Failed to find text file")
        sys.exit()

    temp = input.readlines()


    utsa = []
    uswa = []
    for i in range(1, len(temp)):
        if temp[i].split(',')[0] == "Swartz Bay":
            uswa.append(temp[i].split(','))
        if temp[i].split(',')[0] == "Tsawwassen":
            utsa.append(temp[i].split(','))


    for ele in utsa:
        del ele[17],ele[16],ele[15],ele[14],ele[13],ele[10],ele[9],ele[8],ele[5],ele[4],ele[3],ele[2],ele[1]

    for ele in uswa:
        del ele[17],ele[16],ele[15],ele[14],ele[13],ele[10],ele[9],ele[8],ele[5],ele[4],ele[3],ele[2],ele[1]

    usdelay = 0
    for time in uswa:
        usdelay += (int(time[3])-int(time[1]))*60
        usdelay += int(time[4])-int(time[2])
    usdelay = usdelay/len(uswa)


    utdelay = 0
    for time in utsa:
        utdelay += (int(time[3])-int(time[1]))*60
        utdelay += int(time[4])-int(time[2])
    utdelay = utdelay/len(utsa)







    try:
        input = open('bc_ferries_route1_July2017.csv', "r")
    except:
        print("Failed to find text file")
        sys.exit()

    jtemp = input.readlines()


    jtsa = []
    jswa = []
    for i in range(1, len(jtemp)):
        if jtemp[i].split(',')[0] == "Swartz Bay":
            jswa.append(jtemp[i].split(','))
        if jtemp[i].split(',')[0] == "Tsawwassen":
            jtsa.append(jtemp[i].split(','))


    for ele in jtsa:
        del ele[17],ele[16],ele[15],ele[14],ele[13],ele[10],ele[9],ele[8],ele[5],ele[4],ele[3],ele[2],ele[1]

    for ele in jswa:
        del ele[17],ele[16],ele[15],ele[14],ele[13],ele[10],ele[9],ele[8],ele[5],ele[4],ele[3],ele[2],ele[1]

    jsdelay = 0
    for time in jswa:
        jsdelay += (int(time[3])-int(time[1]))*60
        jsdelay += int(time[4])-int(time[2])
    jsdelay = jsdelay/len(jswa)


    jtdelay = 0
    for time in utsa:
        jtdelay += (int(time[3])-int(time[1]))*60
        jtdelay += int(time[4])-int(time[2])
    jtdelay = jtdelay/len(jtsa)








    try:
        input = open('bc_ferries_route1_Feb2017.csv', "r")
    except:
        print("Failed to find text file")
        sys.exit()

    ftemp = input.readlines()


    ftsa = []
    fswa = []
    for i in range(1, len(ftemp)):
        if ftemp[i].split(',')[0] == "Swartz Bay":
            fswa.append(ftemp[i].split(','))
        if ftemp[i].split(',')[0] == "Tsawwassen":
            ftsa.append(ftemp[i].split(','))


    for ele in ftsa:
        del ele[17],ele[16],ele[15],ele[14],ele[13],ele[10],ele[9],ele[8],ele[5],ele[4],ele[3],ele[2],ele[1]

    for ele in fswa:
        del ele[17],ele[16],ele[15],ele[14],ele[13],ele[10],ele[9],ele[8],ele[5],ele[4],ele[3],ele[2],ele[1]

    fsdelay = 0
    for time in fswa:
        fsdelay += (int(time[3])-int(time[1]))*60
        fsdelay += int(time[4])-int(time[2])
    fsdelay = fsdelay/len(fswa)


    ftdelay = 0
    for time in ftsa:
        ftdelay += (int(time[3])-int(time[1]))*60
        ftdelay += int(time[4])-int(time[2])
    ftdelay = ftdelay/len(ftsa)


    if cond == 's' or cond == 't':      
        print("RESULTS")
        if cond == 's':
            print("Swartz Bay:")
            if(mon == 1):
                print("No delay data for Jan")
            elif(mon == 2):
                print("Average delay for Feb:", fsdelay)
            elif(mon == 3):
                print("No delay data for Mar")
            elif(mon == 4):
                print("No delay data for Apr")
            elif(mon == 5):
                print("No delay data for May")
            elif(mon == 6):
                print("Average delay for Jun:", usdelay)
            elif(mon == 7):
                print("Average delay for Jul:", jsdelay)
            elif(mon == 8):
                print("No delay data for Aug")
            elif(mon == 9):
                print("No delay data for Sep")
            elif(mon == 10):
                print("No delay data for Oct")
            elif(mon == 11):
                print("No delay data for Nov")
            elif(mon == 12):
                print("No delay data for Dec")
            else:
                print("Enter number form 1-12")
            print("END")
            
        elif cond == 't':
            print("Tsawwassen:")
            if(mon == 1):
                print("No delay data for Jan")
            elif(mon == 2):
                print("Average delay for Feb:", ftdelay)
            elif(mon == 3):
                print("No delay data for Mar")
            elif(mon == 4):
                print("No delay data for Apr")
            elif(mon == 5):
                print("No delay data for May")
            elif(mon == 6):
                print("Average delay for Jun:", utdelay)
            elif(mon == 7):
                print("Average delay for Jul:", jtdelay)
            elif(mon == 8):
                print("No delay data for Aug")
            elif(mon == 9):
                print("No delay data for Sep")
            elif(mon == 10):
                print("No delay data for Oct")
            elif(mon == 11):
                print("No delay data for Nov")
            elif(mon == 12):
                print("No delay data for Dec")
            else:
                print("Enter number form 1-12")
            print("END")

        elif cond != 'q':
            print("Enter a s,t or q")

