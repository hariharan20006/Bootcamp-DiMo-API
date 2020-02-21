import pymongo
import requests
from bson.objectid import ObjectId

# DB Connection 
myclient = pymongo.MongoClient("mongodb://localhost:32768/")
mydb = myclient["movies"]
mycol = mydb["movies"]

for movie in mycol.find({"movie_poster_url" : {"$exists" : False}}):
  movie_name = movie['original_title']
  URL = "https://api.themoviedb.org/3/search/movie?api_key=15d2ea6d0dc1d476efbca3eba2b9bbfb&query=" + str(movie_name)
  # sending get request and saving the response as response object 
  r = requests.get(url = URL)
  data = r.json() 
  try:
    total_results = data['total_results']
    if(total_results > 0): 
        movie_poster_url = "http://image.tmdb.org/t/p/w500" + str(data['results'][0]['poster_path'])
        print("Movie ", movie_name , " movie_poster_url " , movie_poster_url)
        myquery = { "_id": ObjectId(movie['_id']) }
        newvalues = { "$set": { "movie_poster_url":  movie_poster_url}}
        mycol.update_one(myquery, newvalues)
    else:
        print("!!!!!! No Movie oster Available for " , movie_name)
  except:
      print("Exception !! ")
print("Done")
