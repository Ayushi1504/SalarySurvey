import csv
import json
import collections
import forex_python
import re
orderedDict = collections.OrderedDict()
from collections import OrderedDict
from forex_python.converter import CurrencyRates

def csv_to_json(csvFilePath, jsonFilePath):
    jsonArray = []
    with open(csvFilePath, encoding='utf-8') as csvf:
        with open(jsonFilePath, 'w', encoding='utf-8') as jsonf:
            csvReader = csv.DictReader(csvf)
            for row in csvReader:
                row = {key.replace('"','').replace(' ','').lower() :val for key, val in row.items()}

                #for key in row:
                    #print(key)

                row['ot']=row['ifother,pleaseindicatethecurrencyhere:']
                del row['ifother,pleaseindicatethecurrencyhere:']

                row['age']=row['howoldareyou?']
                del row['howoldareyou?']

                row['location']=row['whereareyoulocated?(city/state/country)']
                del row['whereareyoulocated?(city/state/country)']

                row['industry']=row['whatindustrydoyouworkin?']
                del row['whatindustrydoyouworkin?']

                row['role']=row['jobtitle']
                del row['jobtitle']

                del row['ifyourjobtitleneedsadditionalcontext,pleaseclarifyhere:']
                row['experience']=row['howmanyyearsofpost-collegeprofessionalworkexperiencedoyouhave?']
                del row['howmanyyearsofpost-collegeprofessionalworkexperiencedoyouhave?']

                row['salary']=row['whatisyourannualsalary?']
                del row['whatisyourannualsalary?']

                row['currency']= row['pleaseindicatethecurrency']
                del row['pleaseindicatethecurrency']

                age_str = row['age'].split(' ')[0].split('-')
                row['age_start'] =age_str[0]
                if(len(age_str)>1):
                    row['age_end']=age_str[1]
                del row['age']

                yoe = re.sub('[^0-9]',' ',row['experience']).split(' ')


                total_exp = 0;
                sz = 0;

                #Taking average of all experience

                for exp in yoe:
                    #print(exp)
                    if(exp.isnumeric()) :
                        sz+=1
                        total_exp+=int(exp)

                row['experience']=(total_exp)/sz


                #float(re.sub('[^0-9]','',row['currency']))

                sal = row['salary'].split('.')
                total_sal=0;

                for sl in sal:
                    mod = re.sub('[^0-9]','',sl)
                    if(mod!=''):
                        total_sal+=float(mod)




                curr = row['currency']

                if(len(curr)!=3):
                    curr=row['ot']

                del row['ot']

                #print(row['currency']+" "+str(total_sal) +"   "+ curr)


                cr = CurrencyRates()

                try:
                    row['compensation']= cr.convert(curr, 'USD',total_sal )
                except:
                    row['compensation']=total_sal
                del row['currency']
                del row['salary']

                #for key in row:
                    #print(key)

                y = json.dumps(row)

                jsonf.write(y)
                jsonf.write("\n")

csvFilePath = r'C:\Users\ayush\OneDrive\Desktop\SalarySurvey\salary_survey-1.csv'
jsonFilePath = r'C:\Users\ayush\OneDrive\Desktop\SalarySurvey\salary_survey-1-output.json'
csv_to_json(csvFilePath, jsonFilePath)
