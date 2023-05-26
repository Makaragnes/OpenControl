from fastapi import FastAPI
from fastapi.responses import JSONResponse
from fastapi.encoders import jsonable_encoder
from fastapi.staticfiles import StaticFiles
import json
app = FastAPI()
with open('./jsons/deps.json') as user_file:
    file_contents = json.load(user_file)
print(file_contents)

app.mount("/static", StaticFiles(directory="static"), name="static")
@app.post("/deps/")
def deps():
    return JSONResponse(file_contents)

