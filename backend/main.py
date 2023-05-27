from fastapi import FastAPI
from fastapi.responses import JSONResponse
from fastapi.encoders import jsonable_encoder
from fastapi.staticfiles import StaticFiles
import json
app = FastAPI()
with open('./jsons/deps.json') as user_file:
    file_contents = json.load(user_file)
print(file_contents)
# resp_cont[19]
with open('./jsons/1.json') as user_file:
    resp_contents = json.load(user_file)
with open('./jsons/1.json') as user_file:
    resp_contents[1] = json.load(user_file)
with open('./jsons/2.json') as user_file:
    resp_contents[2] = json.load(user_file)
with open('./jsons/3.json') as user_file:
    resp_contents[3] = json.load(user_file)
with open('./jsons/4.json') as user_file:
    resp_contents[4] = json.load(user_file)
with open('./jsons/5.json') as user_file:
    resp_contents[5] = json.load(user_file)
with open('./jsons/6.json') as user_file:
    resp_contents[6] = json.load(user_file)
with open('./jsons/7.json') as user_file:
    resp_contents[7] = json.load(user_file)
with open('./jsons/8.json') as user_file:
    resp_contents[8] = json.load(user_file)
with open('./jsons/9.json') as user_file:
    resp_contents[9] = json.load(user_file)
with open('./jsons/10.json') as user_file:
    resp_contents[10] = json.load(user_file)
with open('./jsons/11.json') as user_file:
    resp_contents[11] = json.load(user_file)
with open('./jsons/12.json') as user_file:
    resp_contents[12] = json.load(user_file)
with open('./jsons/13.json') as user_file:
    resp_contents[13] = json.load(user_file)
with open('./jsons/14.json') as user_file:
    resp_contents[14] = json.load(user_file)
with open('./jsons/15.json') as user_file:
    resp_contents[15] = json.load(user_file)
with open('./jsons/16.json') as user_file:
    resp_contents[16] = json.load(user_file)
with open('./jsons/17.json') as user_file:
    resp_contents[17] = json.load(user_file)
with open('./jsons/18.json') as user_file:
    resp_contents[18] = json.load(user_file)
with open('./jsons/19.json') as user_file:
    resp_contents[19] = json.load(user_file)

# for i in range(1,19):
#     with open('./jsons/' + str(i) + '.json') as user_file:
#         resp_cont[i] = json.load(user_file)


app.mount("/static", StaticFiles(directory="static"), name="static")
@app.post("/deps/")
def deps():
    return JSONResponse(file_contents)
###  shitty stile  of  consumming  all of  posts , 
### TO_DO: replace  this  shitt  with  normal post  parameter    
@app.post("/deps/1")
def deps():
    return JSONResponse(resp_contents[1])
@app.post("/deps/2")
def deps():
    return JSONResponse(resp_contents[2])
@app.post("/deps/3")
def deps():
    return JSONResponse(resp_contents[3])
@app.post("/deps/4")
def deps():
    return JSONResponse(resp_contents[4])
@app.post("/deps/5")
def deps():
    return JSONResponse(resp_contents[5])

@app.post("/deps/6")
def deps():
    return JSONResponse(resp_contents[6])

@app.post("/deps/7")
def deps():
    return JSONResponse(resp_contents[7])

@app.post("/deps/8")
def deps():
    return JSONResponse(resp_contents[8])

@app.post("/deps/9")
def deps():
    return JSONResponse(resp_contents[9])

@app.post("/deps/10")
def deps():
    return JSONResponse(resp_contents[10])

@app.post("/deps/11")
def deps():
    return JSONResponse(resp_contents[11])

@app.post("/deps/12")
def deps():
    return JSONResponse(resp_contents[12])

@app.post("/deps/13")
def deps():
    return JSONResponse(resp_contents[13])

@app.post("/deps/14")
def deps():
    return JSONResponse(resp_contents[14])

@app.post("/deps/15")
def deps():
    return JSONResponse(resp_contents[15])

@app.post("/deps/16")
def deps():
    return JSONResponse(resp_contents[16])

@app.post("/deps/17")
def deps():
    return JSONResponse(resp_contents[17])

@app.post("/deps/18")
def deps():
    return JSONResponse(resp_contents[18])

@app.post("/deps/19")
def deps():
    return JSONResponse(resp_contents[19])

