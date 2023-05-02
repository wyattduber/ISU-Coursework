from pymongo import MongoClient
import json

client = MongoClient()
db = client['local']
collection = db['current']
result = dict(db.current.find_one({"photo_id" : "1678574944642"}))

print(result)

for item in result:
	print(item + "thing")
	print(str(item[0]))
	print(str(item[1]))
	print(str(item[2]))
	print(str(item[3]))
