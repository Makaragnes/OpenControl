from fastapi import FastAPI
from fastapi.responses import JSONResponse
from fastapi.encoders import jsonable_encoder
from fastapi.staticfiles import StaticFiles
from pydantic import BaseModel
from fastapi.encoders import jsonable_encoder
import json
from json import JSONEncoder
app = FastAPI()
#preparation  ds  part
class Item(BaseModel) :
    message : str
class Item_Id(BaseModel) :
    item_id: int
class Personinfo(BaseModel) :
    personID: int
    full_name: str
    phone: str
    email: str
    birthday = {
        "day": 0,
        "month": "",
        "year": 0
    }
    address = {
        "postIndex": 0,
        "country": "",
        "city": "",
        "street": "",
        "house": "",
        "flat": ""
    }
    passport = {
        "number": 0,
        "series": 0
    }
class Businessinfo(BaseModel) :
    OGRN : int
    fullTitle: str
    INN: int
    establishedCapital: str
    infoAboutActivity: str
    additionalActivity: str
    nameOfTaxService: str
    shortTitle: str
    dataUGRUL = {
        "day": 0,
        "month": "",
        "year": 0
    }
    businessLocation = {
        "postIndex": 0,
        "country": "",
        "city": "",
        "street": "",
        "house": "",
        "flat": ""
    }
    taxLocation = {
        "postIndex": 0,
        "country": "",
        "sity": "",
        "streat": "",
        "house": "",
        "flat": ""
    }
class BusinessEncoder(json.JSONEncoder):
    def default(self, o):
            return o.__dict__
class PersonEncoder(json.JSONEncoder):
    def default(self, o):
            return o.__dict__

######################
######################
############# initial read
try:
    with open('./jsons/businessInfo.json') as user_file:
        business_inf = json.load(user_file)
finally:
    user_file.close()
try:
    with open('./jsons/personInfo.json') as user_file:
        person_inf = json.load(user_file)
finally:
    user_file.close()


try:
    with open('./jsons/deps.json') as user_file:
        file_contents = json.load(user_file)
finally:
    user_file.close()


try:
    with open('./jsons/chat_bot.json') as user_file:
        chat_bot_json = json.load(user_file)
finally:
    user_file.close()


try:
    with open('./jsons/1.json') as user_file:
        resp_contents = json.load(user_file)
finally:
    user_file.close()
for i in range(1,20):
    try:
        with open('./jsons/' + str(i) + '.json') as user_file:
            resp_contents[i] = json.load(user_file)
    finally:
        user_file.close()
#end preparation ds  part
#########################
#########################
#
#
#
##########################
##########################
##################fapi part


app.mount("/static", StaticFiles(directory="static"), name="static")
@app.post("/deps/")
async def deps():
    return JSONResponse(file_contents)

@app.post("/deps/single/")
async def create_item( item: Item_Id):
    return JSONResponse(resp_contents[item.item_id])

@app.post("/profile/person/init/")
async def init_personal():
    try:
        with open('./jsons/personInfo.json') as user_file:
            person_inf = json.load(user_file)
    finally:
        user_file.close()
        return JSONResponse(person_inf)

@app.post("/profile/business/init/")
async def init_business():
    try:
        with open('./jsons/businessInfo.json') as user_file:
            business_inf = json.load(user_file)
    finally:
        user_file.close()
        return JSONResponse(business_inf)





@app.post("/profile/person/")
async def init_personal(person_info : Personinfo):
    try:
        with open('./jsons/personInfo.json', 'w') as user_file:
            user_file.write(json.dumps(person_info, cls=PersonEncoder))
            person_inf = person_info
    finally:
        return JSONResponse(jsonable_encoder(person_info))


@app.post("/profile/business/")
async def init_business(business_info : Businessinfo):
    try:
        with open('./jsons/businessInfo.json', 'w') as user_file:
            user_file.write(json.dumps(business_info,cls=BusinessEncoder))
            business_inf = business_info
    finally:
        user_file.close()
        return JSONResponse(jsonable_encoder(business_info))

#part  of the  chatbot  api

state = 1

def  change_st(State : int):
    state = State;

x = {
    "message" : ""
}



@app.post("/chatbot/init/")
async def hello_message_resp():
    print(chat_bot_json["hello_mess"])
    # change_st(1) 
    x["message"] = chat_bot_json["hello_mess"]
    print(x)
    return JSONResponse(x)

#version with after all parsing


@app.post("/chatbot/pull/")
async def message_resp(item : Item):
    message = item.message
    if state==1 :
        for i in chat_bot_json["info_vocab"]:
            if (message == str(i)): 
                print(message)
                x["message"] = ''
                x["message"] =  chat_bot_json["link"]
                # y=json.dumps(x)
                return JSONResponse(x)
            else:
                print("faggot")

            if(message.find(i)==-1):
                print("faggot")
            else :
                print(message)
                x["message"] = ''
                x["message"] =  chat_bot_json["link"]
                # y=json.dumps(x)
                return JSONResponse(x)
        for i in chat_bot_json["department_list_vocab"]:
            if (message == str(i)): 
                print(message)
                x["message"] = ''
                # for i in chat_bot_json["department_list"]:
                for i in range(0,19):
                    x["message"] =  x["message"] + "|"+ str(chat_bot_json["department_list"][i]["num"]) +"|"+  chat_bot_json["department_list"][i]["short_name"] 
                return JSONResponse(x)
            else:
                print("faggot")

            if(message.find(i)==-1):
                print("faggot")
                # TO_DO:  add  the other cases for this  term
            else :
                print(message)
                change_st(2)

                x["message"] = ''
                # return JSONResponse(x)
                for i in range(0,19):
                    x["message"] =  x["message"] + "|" + str(chat_bot_json["department_list"][i]["num"])+ "|" + chat_bot_json["department_list"][i]["short_name"]  
                return JSONResponse(x)
        for i in range (1,20):
            x["message"] = ''
            if(message == str(resp_contents[i]["short_name"])):
                print(message)
                change_st(1)
                # return JSONResponse(resp_contents[i])
                # x["message"] = resp_contents[i];
                x["message"] = "Департамент :" + str(resp_contents[i]["department"])  + "|"
                for j in resp_contents[i]["controll_type"]:
                    x["message"] = x["message"]  + "Вид надзора, контроля :" + "|" +  str(j["type"]) + "|" 
                    for k in j["consult"]:
                        x["message"] = x["message"]  + "Темы  консультации :" + "|" +  str(k["theme"]) + "|" 
                return JSONResponse(x)
                # TO_DO:  add  the other cases for this  term
            else :
                print("faggot2")
            if(message.find(resp_contents[i]["short_name"])==-1):
                print("faggot2")
                if(message==str(i)): 
                    print(message)
                    change_st(1)
                    x["message"] = "Департамент : " + str(resp_contents[i]["department"])  + "|"
                    for j in resp_contents[i]["controll_type"]:
                        x["message"] = x["message"]  + "Вид надзора, контроля :"+ "|"  +  str(j["type"])  + "|"
                        for k in j["consult"]:
                            x["message"] = x["message"]  + "Темы  консультации :"+ "|"  +  str(k["theme"])  + "|"
                    return JSONResponse(x)
                else:
                    print("Faggot 3 ")
                    

                if(message.find(str(i))==-1): 
                    print("Faggot 3 ")
                else:
                    print(message)
                    change_st(1)
                    # x["message"] = resp_contents[i];
                    x["message"] = "Департамент : " + str(resp_contents[i]["department"])  + "|"
                    for j in resp_contents[i]["controll_type"]:
                        x["message"] = x["message"]  + "Вид надзора, контроля :" +  str(j["type"])  + "|"
                        for k in j["consult"]:
                            x["message"] = x["message"]  + "Темы  консультации :" +  str(k["theme"])+ "|"
                    return JSONResponse(x)
                # TO_DO:  add  the other cases for this  term
            else :
                print(message)
                change_st(1)
                x["message"] = ''
                x["message"] = resp_contents[i];
                return JSONResponse(x)
        change_st(3)
        x["message"] = ''
        x["message"] = chat_bot_json["department_no"]
        return JSONResponse(x)
    return JSONResponse(x)
