# library and dataset
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


problems = list(table.keys())

pairs=list(table.values())
countries=list(pairs[0].keys())
print(countries)

for j in countries:  
    data=[]
    for i in problems:
        d=table.get(i).get(j)
        years =d.keys()
        data1=[]
        x = np.array(convertToInt(years))
        for k in years:
            data1.append(d.get(k))
        y=np.array(convertToDouble(data1))
        
        data.append(y)
        
        
    data=np.array(data)  
    print(data)
    m=np.random.choice(['o', '.', ',', 'x', '+', 'v', '^', '<', '>', 's', 'd'],size=1)[0]
    rgb = np.random.rand(3,)
    plt.scatter(data[0], data[1], marker=m, s=50, color=rgb,label=j)
    
    

plt.xlabel(problems[0]) 
plt.ylabel(problems[1])
plt.legend(loc="upper center", bbox_to_anchor=(0.9, 1.15), ncol=2)
    
plt.show()   
    
   
