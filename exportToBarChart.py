import matplotlib.pyplot as plt
import numpy as np


f = open("/home/nvlacho/eclipse-workspace/databaseMYE030/hashTable.txt", "r")

table = dict()
exportCase = ""
key1 = ""
key2 = ""

lines = f.readlines()

exportCase = lines[0].strip()

lineCount = 1

x = 1

while(True):

    

    #lineCount += 1

    data1 = lines[x].strip()
    x += 1
    data3 = ""

    tableWithCounries = dict()
    

    if data1 != "" :
        key1 = data1
        

    while(True):
        tableWithYears = dict()
        if data3 == "" :
            data2 = lines[x].strip()
            x += 1
            #lineCount += 1
            if not(data2 == "\n") and not(data2 == "") :
                key2 = data2

        text = lines[x].strip()
        x += 1
        #lineCount += 1
        strArray = text.split(",")
        for i in range(0 , len(strArray), 2):
            if (i+1) < len(strArray):
                tableWithYears[strArray[i]] = strArray[i+1]
                
            else:
                break

        tableWithCounries[key2] = tableWithYears
        
        if len(lines) > x:
            data3 = lines[x]
            x += 1
            #lineCount += 1

        if data3 == "\n" or not(len(lines)>x):
            break

        if data3 != "\n" :
            key2 = data3.strip()
            continue
        if data3 == "":
            break
    
    table[key1] = tableWithCounries

    if not(len(lines) > x):
        break

    if data3 == "\n" and len(lines) > x:
        continue

    if data3 == "":
        break


def convertToInt(x):
    array = []
    for i in list(x):
        array.append(int(i))
    return array

def convertToDouble(x):
    array = []
    for i in list(x):
        try:
            array.append(float(i)) 
        except:
            print("Please insert a valid number. Currencies cannot contain commas, spaces, or characters.")

    return array


problems = table.keys()

for i in problems:
    plt.title(i) 
    plt.xlabel("Years") 
    plt.ylabel("Data")
    
    tableWithCountries = table.get(i)

    countries = table.get(i).keys()
    
    years = []
    data=[]
    for j in countries:
        
        tableWithYears = table.get(i).get(j)
        years = tableWithYears.keys()
        data1 = []
        for k in years:
            data1.append(tableWithYears.get(k))
        data.append(data1)
       
        
        
        
    
    x = np.array(convertToInt(years))
    gap = .10 / len(data)
    c=list(countries)
    for i, row in enumerate(data): 
        X = np.arange(len(row))
        color_list =np.random.rand(3,)
        y = np.array(convertToDouble(row))
        plt.bar(X + i * gap, y, width = gap, label=c[i],color = color_list)
        
    
    e=list(convertToInt(years))
    y=sorted(e)
    print(y)
    plt.xticks(range(len(years)), y)
    plt.legend(loc="upper center", bbox_to_anchor=(0.5, 1.15), ncol=2)
    
    plt.show()


