from fastapi import FastAPI
from fastapi.responses import JSONResponse
from fastapi.encoders import jsonable_encoder
from fastapi.staticfiles import StaticFiles
from pydantic import BaseModel
from fastapi.encoders import jsonable_encoder
import json
from json import JSONEncoder
import sys
import importlib


app = FastAPI()
#preparation  ds  part
class Item(BaseModel) :
    message : str
class Item_Id(BaseModel) :
    item_id: int
class Personinfo(BaseModel) :
    Id : str
    personID: int
    full_name: str
    phone: str
    email: str
    birthday = {
        "day": "",
        "month": "",
        "year": ""
    }
    address = {
        "postIndex": "",
        "country": "",
        "city": "",
        "street": "",
        "house": "",
        "flat": ""
    }
    passport = {
        "number": "",
        "series": ""
    }
class Businessinfo(BaseModel) :
    Id : str
    OGRN : str
    fullTitle: str
    INN: str
    establishedCapital: str
    infoAboutActivity: str
    additionalActivity: str
    nameOfTaxService: str
    shortTitle: str
    dataUGRUL = {
        "day": "",
        "month": "",
        "year": ""
    }
    businessLocation = {
        "postIndex": "",
        "country": "",
        "city": "",
        "street": "",
        "house": "",
        "flat": ""
    }
    taxLocation = {
        "postIndex": "",
        "country": "",
        "sity": "",
        "streat": "",
        "house": "",
        "flat": ""
    }
class Calenda(BaseModel):
    day: int
    month: int
    year: int
class Day(BaseModel):
    day_num: int
    month_num: int
    year: int
    time =  [
            {
                "time_val" : "8:00-9:00", 
                "is_busy" : 0
            },
            {
                "time_val" : "9:00-10:00", 
                "is_busy" : 0
            },
            {
                "time_val" : "10:00-11:00", 
                "is_busy" : 0
            },
            {
                "time_val" : "11:00-12:00", 
                "is_busy" : 0
            },
            {
                "time_val" : "12:00-13:00", 
                "is_busy" : 0
            },
            {
                "time_val" : "13:00-14:00", 
                "is_busy" : 0
            },
            {
                "time_val" : "14:00-15:00", 
                "is_busy" : 0
            },
            {
                "time_val" : "15:00-16:00", 
                "is_busy" : 0
            },
            {
                "time_val" : "16:00-17:00", 
                "is_busy" : 0
            },
            {
                "time_val" : "17:00-18:00", 
                "is_busy" : 0
            },
            {
                "time_val" : "18:00-19:00", 
                "is_busy" : 0
            },
            {
                "time_val" : "20:00-21:00", 
                "is_busy" : 0
            }
        ]
class Token(BaseModel):
    token_val : str
class User_hold():
    user = [
    { 
        "user_id" : 0,
        "email" : "",
        "token" : "",
        "role"  : "user",
        "department" : "",
        "filledOut": False,
        "consult_status": [ 
            { 
                
                "status" : 0,
                "consult" : [ 
                    {
                        "theme" : "",
                        "date_time" : "" 
                    }
                ] 
            },
            { 
                
                "status" : 1,
                "consult" : [ 
                    {
                        "theme" : "",
                        "date_time" : "" 
                    }
                ] 
            }
        ]
    }
]
class Consult(BaseModel):
    token     : str
    user_id   : int
    theme     : str
    department: str
    date_time : str
    cons_id   : int
    status    : bool
class Consult_hold():
    # cons_num : int
    consult_status = [ 
                { 
                    
                    "status" : 0,
                    "consult" : [ 
                        {
                            "theme" : "",
                            "department" : "",
                            "date_time" : "", 
                            "status" : "",
                            "cons_id" :0
                        }
                    ] 
                }
            ]
class Auth(BaseModel):
    email : str
    token : str
    id    : int
    role  : str
    department: str
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
    with open('./jsons/1_base.json') as user_file:
        calend_inf = json.load(user_file)
finally:
    user_file.close()

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
async def init_personal(token : Token):
    try:
        with open('./jsons/personInfo.json') as user_file:
            person_inf = json.load(user_file)
    finally:
        user_file.close()
    try:
        with open('./jsons/personInfo.json', 'w') as user_file1:
            person_inf["pi"][0]["Id"] = ""
            person_inf["pi"][0]["full_name"] = ""
            person_inf["pi"][0]["phone"] = ""
            person_inf["pi"][0]["email"] = ""
            person_inf["pi"][0]["birthday"] =  {
                                                    "day": "",
                                                    "month": "",
                                                    "year": ""
                                                }
            person_inf["pi"][0]["address"] = {
                                                "postIndex": "",
                                                "country": "",
                                                "city": "",
                                                "street": "",
                                                "house": "",
                                                "flat": ""
                                            }
            person_inf["pi"][0]["pasport"] = {
                                                "number": "",
                                                "series": ""
                                            }
            user_file1.write(json.dumps(person_inf))                                 
    finally:
        user_file.close()
    for i in person_inf["pi"]:
        if (i["Id"]== token.token_val): 
            return JSONResponse(jsonable_encoder(i))
    return JSONResponse(jsonable_encoder(person_inf["pi"][0]))


@app.post("/profile/business/init/")
async def init_business(token : Token):
    try:
        with open('./jsons/businessInfo.json') as user_file:
            business_inf = json.load(user_file)
    finally:
        user_file.close()
    try:
        with open('./jsons/businessInfo.json', 'w') as user_file1:
            business_inf["bi"][0]["Id"] = ""
            business_inf["bi"][0]["OGRN"] = ""
            business_inf["bi"][0]["fullTitle"] = ""
            business_inf["bi"][0]["INN"] = ""
            business_inf["bi"][0]["establishedCapital"] = ""
            business_inf["bi"][0]["infoAboutActivity"] = ""
            business_inf["bi"][0]["additionalActivity"] = ""
            business_inf["bi"][0]["nameOfTaxService"] = ""
            business_inf["bi"][0]["shortTitle"] = ""
            business_inf["bi"][0]["dataUGRUL"] =  {
                                                "day": "",
                                                "month": "",
                                                "year": ""
                                            }
            business_inf["bi"][0]["businessLocation"] = {
                                                "postIndex": "",
                                                "country": "",
                                                "city": "",
                                                "street": "",
                                                "house": "",
                                                "flat": ""
                                            }
            business_inf["bi"][0]["taxLocation"] = {
                                                "postIndex": "",
                                                "country": "",
                                                "sity": "",
                                                "streat": "",
                                                "house": "",
                                                "flat": ""
                                            }
            user_file1.write(json.dumps(business_inf))                                 
    finally:
        user_file.close()
    for i in business_inf["bi"]:
        if (i["Id"]== token.token_val): 
            return JSONResponse(jsonable_encoder(i))
    return JSONResponse(jsonable_encoder(business_inf["bi"][0]))

@app.post("/profile/person/")
async def init_personal(person_info : Personinfo,token : Token):
    try:
        with open('./jsons/user_db1.json') as user_file:
            auth_inf = json.load(user_file)
    finally:
        user_file.close()
    try:
        with open('./jsons/personInfo.json') as user_file:
            person_inf = json.load(user_file)
    finally:
        user_file.close()

    for i in person_inf["pi"]:
        if (i["Id"]== token.token_val):
            try:
                with open('./jsons/personInfo.json', 'w') as user_file:
                    i["Id"] = token.token_val
                    i["full_name"] = person_info.full_name
                    i["phone"] = person_info.phone
                    i["email"] = person_info.email
                    i["birthday"] = person_info.birthday
                    i["address"] = person_info.address
                    i["pasport"] = person_info.passport
                    user_file.write(json.dumps(person_inf))
                    return JSONResponse(jsonable_encoder(person_info))                    
            finally:
                user_file.close()
    try:
        with open('./jsons/personInfo.json', 'w') as user_file1:
            person_inf["pi"][0]["Id"] = token.token_val
            person_inf["pi"][0]["full_name"] = person_info.full_name
            person_inf["pi"][0]["phone"] = person_info.phone
            person_inf["pi"][0]["email"] = person_info.email
            person_inf["pi"][0]["birthday"] = person_info.birthday
            person_inf["pi"][0]["address"] = person_info.address
            person_inf["pi"][0]["pasport"] = person_info.passport
            person_inf["pi"].append(person_inf["pi"][0])
            user_file1.write(json.dumps(person_inf))
            for i in auth_inf["user"]:
                if (i["token"] == token.token_val):
                    try:
                        with open('./jsons/user_db1.json', 'w') as user_file2:
                            i["filledOut"] = True
                            user_file2.write(json.dumps(auth_inf))
                    finally:
                        user_file2.close()

            return JSONResponse(jsonable_encoder(person_info))
    finally:
        user_file.close()



@app.post("/profile/filling/")
async def profile_filling(token : Token):
    try:
        with open('./jsons/user_db1.json') as user_file:
            auth_inf = json.load(user_file)
    finally:
        user_file.close()
    for i in auth_inf["user"]:
        if (i["token"] == token.token_val):
            return JSONResponse(jsonable_encoder(i["filledOut"]))

@app.post("/profile/business/")
async def init_business(business_info : Businessinfo, token : Token):
    try:
        with open('./jsons/user_db1.json') as user_file:
            auth_inf = json.load(user_file)
    finally:
        user_file.close()
    try:
        with open('./jsons/businessInfo.json') as user_file:
            business_inf = json.load(user_file)
    finally:
        user_file.close()

    for i in business_inf["bi"]:
        if (i["Id"]== token.token_val):
            try:
                with open('./jsons/businessInfo.json', 'w') as user_file:
                    i["Id"] = token.token_val
                    i["OGRN"] = business_info.OGRN
                    i["fullTitle"] = business_info.fullTitle
                    i["INN"] = business_info.INN
                    i["establishedCapital"] = business_info.establishedCapital
                    i["infoAboutActivity"] = business_info.infoAboutActivity
                    i["additionalActivity"] = business_info.additionalActivity
                    i["nameOfTaxService"] = business_info.nameOfTaxService
                    i["shortTitle"] = business_info.shortTitle
                    i["dataUGRUL"] =  business_info.dataUGRUL
                    i["businessLocation"] = business_info.businessLocation
                    i["taxLocation"] = business_info.taxLocation
                    user_file.write(json.dumps(business_inf))
                    return JSONResponse(jsonable_encoder(business_info))                    
            finally:
                user_file.close()
    try:
        with open('./jsons/businessInfo.json', 'w') as user_file1:
            business_inf["bi"][0]["Id"] = token.token_val
            business_inf["bi"][0]["OGRN"] = business_info.OGRN
            business_inf["bi"][0]["fullTitle"] = business_info.fullTitle
            business_inf["bi"][0]["INN"] = business_info.INN
            business_inf["bi"][0]["establishedCapital"] = business_info.establishedCapital
            business_inf["bi"][0]["infoAboutActivity"] = business_info.infoAboutActivity
            business_inf["bi"][0]["additionalActivity"] = business_info.additionalActivity
            business_inf["bi"][0]["nameOfTaxService"] = business_info.nameOfTaxService
            business_inf["bi"][0]["shortTitle"] = business_info.shortTitle
            business_inf["bi"][0]["dataUGRUL"] =  business_info.dataUGRUL
            business_inf["bi"][0]["businessLocation"] = business_info.businessLocation
            business_inf["bi"][0]["taxLocation"] = business_info.taxLocation
            business_inf["bi"].append(business_inf["bi"][0])
            user_file1.write(json.dumps(business_inf))
            for i in auth_inf["user"]:
                if (i["token"] == token.token_val):
                    try:
                        with open('./jsons/user_db1.json', 'w') as user_file2:
                            i["filledOut"] = True
                            user_file2.write(json.dumps(auth_inf))
                    finally:
                        user_file2.close()

            return JSONResponse(jsonable_encoder(business_info))
    finally:
        user_file.close()
##########################
##########################
###### Part  of  calenda
@app.post("/calenda/init/")
async def init_calenda(calenda_info : Calenda):
    try:
        with open('./jsons/1_base.json') as user_file:
            calend_inf = json.load(user_file)
    finally:
        user_file.close()
    for i in range(0,3):
        if (int(calend_inf["month"][i]["num"]) == calenda_info.month):
            for j in calend_inf["month"][i]["day"]:
                if (calenda_info.day == j["day_num"]):
                    return(JSONResponse(jsonable_encoder(j["time"])))
@app.post("/calenda/inwrite/")
async def init_calenda(day : Day):
    try:
        with open('./jsons/1_base.json') as user_file:
            calend_inf = json.load(user_file)
            for i in range(0,3):
                if (int(calend_inf["month"][i]["num"]) == day.month_num):
                    for j in calend_inf["month"][i]["day"]:
                        if (day.day_num == j["day_num"]):
                            j["time"] = jsonable_encoder(day.time)
                            print(j["time"][0])
                            print(day.time[0])
                            user_file.close()
                            try:
                                with open('./jsons/1_base.json', 'w') as user_file:
                                    user_file.write(json.dumps(calend_inf,cls=BusinessEncoder))

                            finally:
                                user_file.close()
                                return JSONResponse(jsonable_encoder(day.time))
            # user_file.write(json.dumps(business_info,cls=BusinessEncoder))
    finally:
        user_file.close()
    # for i in range(0,3):
    #     if (int(calend_inf["month"][i]["num"]) == calenda_info.month):
    #         for j in calend_inf["month"][i]["day"]:
    #             if (calenda_info.day == j["day_num"]):
    #                 return(JSONResponse(jsonable_encoder(j["time"])))

##########################
##########################
##########AUTH part

@app.post("/auth/")
async def auth(auth : Auth):
    try:
        with open('./jsons/user_db1.json') as user_file:
            auth_inf = json.load(user_file)
    finally:
        user_file.close()
    for i in auth_inf["user"]:
        if (i["email"]==auth.email):
            auth.email = "0"
            auth.token = "0"
            auth.id    =  0
            return JSONResponse(jsonable_encoder(auth))
    auth_inf["num"] = auth_inf["num"] + 1
    auth.id = auth_inf["num"]
    luser = User_hold
    luser.user[0]["user_id"] = auth_inf["num"]
    luser.user[0]["email"] = auth.email
    luser.user[0]["token"] = auth.token
    auth_inf["user"].append(jsonable_encoder(luser.user[0]))
    print(auth_inf["user"])
    try:
        with open('./jsons/user_db1.json','w') as user_file:
            # auth_inf = json.load(user_file)
            user_file.write(json.dumps(auth_inf))
            return JSONResponse(jsonable_encoder(auth))
    finally:
        user_file.close()
@app.post("/validate/")
async def validate(auth : Auth):
    try:
        with open('./jsons/user_db1.json') as user_file:
            auth_inf = json.load(user_file)
    finally:
        user_file.close()
    for i in auth_inf["user"]:
        # if (i["user_id"] == auth.id): 
        if (i["email"]==auth.email):
            if (i["token"]== auth.token):
                auth.id=i["user_id"]
                auth.role = i["role"]
                auth.department = i["department"]
                return JSONResponse(jsonable_encoder(auth))
    auth.id=0
    auth.token='0'
    auth.email='0' 
    return JSONResponse(jsonable_encoder(auth))

##########################
##########################
#part of consulting  history
@app.post("/consult/storage")
async def consult_add(consult : Consult):
    try:
        with open('./jsons/user_db1.json') as user_file:
            auth_inf = json.load(user_file)
    finally:
        user_file.close()
    for i in auth_inf["user"]:
        if (i["user_id"] == consult.user_id): 
            if(i["token"] == consult.token):
                consalt_addition = Consult_hold 
                auth_inf["cons_num"] = auth_inf["cons_num"] + 1
                consalt_addition.consult_status[0]["consult"][0]["theme"] = consult.theme
                consalt_addition.consult_status[0]["consult"][0]["department"] = consult.department
                consalt_addition.consult_status[0]["consult"][0]["date_time"] = consult.date_time 
                consalt_addition.consult_status[0]["consult"][0]["cons_id"] = auth_inf["cons_num"] 
                consalt_addition.consult_status[0]["consult"][0]["status"] = consult.status 

                i["consult_status"][0]["consult"].append(jsonable_encoder(consalt_addition.consult_status[0]["consult"][0])) #####like already  has  been  pushed back
                print(i)
                try:
                    with open('./jsons/user_db1.json','w') as user_file:
                        # auth_inf = json.load(user_file)
                        user_file.write(json.dumps(auth_inf))
                        return JSONResponse(jsonable_encoder(consult))
                finally:
                    user_file.close()
    consult.theme ='0'
    consult.department ='0'
    consult.date_time = '0'
    consult.cons_id=0
    consult.status=0
    return JSONResponse(jsonable_encoder(consult))


@app.post("/consult/get_storage")
async def consult_add(consult : Consult):
    try:
        with open('./jsons/user_db1.json') as user_file:
            auth_inf = json.load(user_file)
    finally:
        user_file.close()
    for i in auth_inf["user"]:
        if (i["user_id"] == consult.user_id): 
            if(i["token"] == consult.token):
                consalt_addition = Consult_hold 
                consalt_addition.consult_status[0]["consult"]=i["consult_status"][0]["consult"]
                print(i)
                return JSONResponse(jsonable_encoder(consalt_addition.consult_status[0]["consult"][1:]))
    consult.theme ='0'
    consult.department ='0'
    consult.date_time = '0'
    consult.cons_id=0
    consult.status=0
    return JSONResponse(jsonable_encoder(consult))




###########################
###########################
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
                print("the bot  says NOOO")

            if(message.find(i)==-1):
                print("the bot  says NOOO")
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
                print("the bot  says NOOO")

            if(message.find(i)==-1):
                print("the bot  says NOOO")
                # TO_DO:  add  the other cases for this  term
            else :
                print(message)
                change_st(2)

                x["message"] = ''
                # return JSONResponse(x)
                for i in range(0,19):
                    x["message"] =  x["message"] + "\n\r" + str(chat_bot_json["department_list"][i]["num"])+ "\n\r" + chat_bot_json["department_list"][i]["short_name"]  
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
                    x["message"] = x["message"]  + "Вид надзора, контроля :" + "\n\r" +  str(j["type"]) + "\n\r" 
                    for k in j["consult"]:
                        x["message"] = x["message"]  + "Темы  консультации :" + "\n\r" +  str(k["theme"]) + "\n\r" 
                return JSONResponse(x)
                # TO_DO:  add  the other cases for this  term
            else :
                print("the bot  says NOOO")
            if(message.find(resp_contents[i]["short_name"])==-1):
                print("the bot  says NOOO")
                if(message==str(i)): 
                    print(message)
                    change_st(1)
                    x["message"] = "Департамент : " + str(resp_contents[i]["department"])  + "|"
                    for j in resp_contents[i]["controll_type"]:
                        x["message"] = x["message"]  + "Вид надзора, контроля :"+ "\n\r"  +  str(j["type"])  + "\n\r"
                        for k in j["consult"]:
                            x["message"] = x["message"]  + "Темы  консультации :"+ "\n\r"  +  str(k["theme"])  + "\n\r"
                    return JSONResponse(x)
                else:
                    print("the bot  says NOOO")
                    

                if(message.find(str(i))==-1): 
                    print("the bot  says NOOO")
                else:
                    print(message)
                    change_st(1)
                    # x["message"] = resp_contents[i];
                    x["message"] = "Департамент : " + str(resp_contents[i]["department"])  + "\n\r"
                    for j in resp_contents[i]["controll_type"]:
                        x["message"] = x["message"]  + "Вид надзора, контроля :" +  str(j["type"])  + "\n\r"
                        for k in j["consult"]:
                            x["message"] = x["message"]  + "Темы  консультации :" +  str(k["theme"])+ "\n\r"
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
