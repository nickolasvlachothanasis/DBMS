import csv

csvFiles = ["/media/nvlacho/myDATA/DATA/cs/MYE030/Project/DatabaseFiles/hapiscore_whr.csv",
            "/media/nvlacho/myDATA/DATA/cs/MYE030/Project/DatabaseFiles/hdi_human_development_index.csv",
            "/media/nvlacho/myDATA/DATA/cs/MYE030/Project/DatabaseFiles/natural_gas_production_per_person.csv",
            "/media/nvlacho/myDATA/DATA/cs/MYE030/Project/DatabaseFiles/natural_gas_production_total.csv",
            "/media/nvlacho/myDATA/DATA/cs/MYE030/Project/DatabaseFiles/natural_gas_proved_reserves_total.csv",
            "/media/nvlacho/myDATA/DATA/cs/MYE030/Project/DatabaseFiles/natural_gas_proven_reserves_per_person.csv",
            "/media/nvlacho/myDATA/DATA/cs/MYE030/Project/DatabaseFiles/nuclear_power_generation_per_person.csv",
            "/media/nvlacho/myDATA/DATA/cs/MYE030/Project/DatabaseFiles/nuclear_power_generation_total.csv",
            "/media/nvlacho/myDATA/DATA/cs/MYE030/Project/DatabaseFiles/oil_consumption_per_cap.csv",
            "/media/nvlacho/myDATA/DATA/cs/MYE030/Project/DatabaseFiles/oil_consumption_total.csv",
            "/media/nvlacho/myDATA/DATA/cs/MYE030/Project/DatabaseFiles/oil_production_per_person.csv",
            "/media/nvlacho/myDATA/DATA/cs/MYE030/Project/DatabaseFiles/oil_production_total.csv",
            "/media/nvlacho/myDATA/DATA/cs/MYE030/Project/DatabaseFiles/population_total.csv",
            "/media/nvlacho/myDATA/DATA/cs/MYE030/Project/DatabaseFiles/si_pov_gini.csv"]

# ---------------------------COUNTRIES------------------------

countryName = []
countryNameWithId=[]
for x in csvFiles:
    filePath = ""
    filePath = x
    with open(filePath) as csv_file:
        csv_reader = csv.reader(csv_file, delimiter=',')
        for Column in csv_reader:
            if Column[0] not in countryName and Column[0]!="country":
                countryName.append(Column[0])
            
sorted(countryName)

with open('/media/nvlacho/myDATA/DATA/cs/MYE030/Project/DatabaseFiles/countryNames.csv', mode='w') as csv_file:
    fieldnames = ['countryId', 'countryName']
    writer = csv.DictWriter(csv_file, fieldnames=fieldnames)

    writer.writeheader()
    i = 0
    for x in countryName:

        countryNameWithId.append([i,x])
        writer.writerow({'countryId': i, 'countryName': x})  
        i += 1              
 

#------------- FOR YEARS --------------

years = []        
for x in csvFiles:
    filePath = ""
    filePath = x
    with open(filePath) as csv_file:
        csv_reader = csv.reader(csv_file, delimiter=',')
        y = 0
        for Column in csv_reader:
            tempArray = []
            if y == 0:
                for j in Column[1:len(Column)] :
                    tempArray.append(j)
                for j in tempArray:
                    if j not in years:
                        years.append(j)
                y = 1
                continue
            else:
                break

years = sorted(years)

with open('/media/nvlacho/myDATA/DATA/cs/MYE030/Project/DatabaseFiles/years.csv', mode='w') as csv_file:
    fieldnames = ['yearId']
    writer = csv.DictWriter(csv_file, fieldnames=fieldnames)

    writer.writeheader()
    for x in years:
        writer.writerow({'yearId': x})
    

# ------------- FOR DATA--------------------------------------

csvFilesToWrite = ["/media/nvlacho/myDATA/DATA/cs/MYE030/Project/DatabaseFiles/finalCsvFiles/hapiscore_whrData.csv",
                    "/media/nvlacho/myDATA/DATA/cs/MYE030/Project/DatabaseFiles/finalCsvFiles/hdi_human_development_indexData.csv",
                    "/media/nvlacho/myDATA/DATA/cs/MYE030/Project/DatabaseFiles/finalCsvFiles/natural_gas_production_per_personData.csv",
                    "/media/nvlacho/myDATA/DATA/cs/MYE030/Project/DatabaseFiles/finalCsvFiles/natural_gas_production_totalData.csv",
                    "/media/nvlacho/myDATA/DATA/cs/MYE030/Project/DatabaseFiles/finalCsvFiles/natural_gas_proved_reserves_totalData.csv",
                    "/media/nvlacho/myDATA/DATA/cs/MYE030/Project/DatabaseFiles/finalCsvFiles/natural_gas_proven_reserves_per_personData.csv",
                    "/media/nvlacho/myDATA/DATA/cs/MYE030/Project/DatabaseFiles/finalCsvFiles/nuclear_power_generation_per_personData.csv",
                    "/media/nvlacho/myDATA/DATA/cs/MYE030/Project/DatabaseFiles/finalCsvFiles/nuclear_power_generation_totalData.csv",
                    "/media/nvlacho/myDATA/DATA/cs/MYE030/Project/DatabaseFiles/finalCsvFiles/oil_consumption_per_capData.csv",
                    "/media/nvlacho/myDATA/DATA/cs/MYE030/Project/DatabaseFiles/finalCsvFiles/oil_consumption_totalData.csv",
                    "/media/nvlacho/myDATA/DATA/cs/MYE030/Project/DatabaseFiles/finalCsvFiles/oil_production_per_personData.csv",
                    "/media/nvlacho/myDATA/DATA/cs/MYE030/Project/DatabaseFiles/finalCsvFiles/oil_production_totalData.csv",
                    "/media/nvlacho/myDATA/DATA/cs/MYE030/Project/DatabaseFiles/finalCsvFiles/population_totalData.csv",
                    "/media/nvlacho/myDATA/DATA/cs/MYE030/Project/DatabaseFiles/finalCsvFiles/si_pov_giniData.csv"]

count = 0
for x in csvFiles:
    FinalDataCsv=[]
    filePath = ""
    filePath = x
    with open(filePath) as csv_file:
        
        csv_reader = csv.reader(csv_file, delimiter=',')
        y = 0
        for Column in csv_reader:
            
            tempData = []
            if y == 0:
                tempYears = []
                for j in Column[1:len(Column)] :
                    tempYears.append(j)
                y = 1
                continue
            else:
                for j in Column[1:len(Column)] :
                    tempData.append(j)
                    n = 0
                for i in tempYears:
                    FinalDataCsv.append([Column[0],i,tempData[n]])
                    n += 1

    with open(csvFilesToWrite[count], mode='w') as csv_file:
        fieldnames = ['Id','Year','Data']
        writer = csv.DictWriter(csv_file, fieldnames=fieldnames)

        writer.writeheader()
        
        for x in FinalDataCsv:
            tepmID = 0 
            for y in countryNameWithId:
                if x[0] == y[1]:
                    #tepmID = y[0]
                    if x[2]=='':
                        writer.writerow({'Id': y[0],'Year':x[1],'Data':0})
                    else:
                        writer.writerow({'Id': y[0],'Year':x[1],'Data':x[2]})
                    break
        count += 1
                
        
        

