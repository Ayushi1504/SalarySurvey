import sys
import json
from datetime import datetime
from elasticsearch import Elasticsearch

es = Elasticsearch(
    hosts="https://localhost:9200",
    basic_auth=('elastic', 'pass*'),
    ca_certs=r"C:\Users\ayush\OneDrive\Desktop\elasticsearch-8.11.3\config\certs\http.p12",
    verify_certs=False
)

with open(r'C:\Users\ayush\OneDrive\Desktop\SalarySurvey\salary_survey-1-output.json', 'r') as file:
    data = file.readlines()

for i, line in enumerate(data):
    json_obj = json.loads(line)
    timestamp_str = json_obj['timestamp']
    timestamp = datetime.strptime(timestamp_str, '%m/%d/%Y %H:%M:%S')
    json_obj['timestamp'] = timestamp.isoformat()

    if(len(json_obj['age_start'])>2):
        del json_obj['age_start']

    res = es.index(index='salary_survey', id=i, body=json_obj)
    print(res)
