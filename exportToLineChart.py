import matplotlib.pyplot as plt
import numpy as np
import pandas as pd

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
    #plt.title(i) 
    #plt.xlabel("Years") 
    #plt.ylabel("Data")
    
    tableWithCountries = table.get(i)
    print('tableWithCountries : ', tableWithCountries )

    countries = table.get(i).keys()
    print("countries", countries)
    data = []
    for j in countries:
        
        tableWithYears = table.get(i).get(j)
        years = tableWithYears.keys()
        
        x = np.array(convertToInt(years))
        data = []
        for k in years:
            data.append(tableWithYears.get(k))
        
        y = np.array(convertToDouble(data))
        print(y)
        
        rgb = np.random.rand(3,)
        lstyle=np.random.choice(['dashdot', 'solid','dashdot', 'dashed', 'dotted'], size=1)[0]
        m=np.random.choice(['o', '.', ',', 'x', '+', 'v', '^', '<', '>', 's', 'd'],size=1)[0]

        plt.plot( x, y, marker=m, color=rgb, linewidth=2, linestyle=lstyle,label=j+','+ i)
        plt.legend(loc="upper center", bbox_to_anchor=(0.5, 1.15), ncol=2)
        
    #plt.legend(loc="lower center", bbox_to_anchor=(0.5, -0.15), ncol= 3)
    #plt.legend()
    #plt.show()
   

plt.title("all") 
plt.xlabel("Years") 
plt.ylabel("Data")
plt.legend()
plt.legend(loc="lower center", bbox_to_anchor=(0.5, -0.15), ncol= 3)
plt.show()

    