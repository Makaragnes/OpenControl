from fastapi import FastAPI
from fastapi.responses import JSONResponse
from fastapi.encoders import jsonable_encoder
from fastapi.staticfiles import StaticFiles


import json
app = FastAPI()
#preparation  ds  part
with open('./jsons/deps.json') as user_file:
    file_contents = json.load(user_file)

with open('./jsons/chat_bot.json') as user_file:
    chat_bot_json = json.load(user_file)

with open('./jsons/1.json') as user_file:
    resp_contents = json.load(user_file)

for i in range(1,20):
    with open('./jsons/' + str(i) + '.json') as user_file:
        resp_contents[i] = json.load(user_file)
#end preparation ds  part
app.mount("/static", StaticFiles(directory="static"), name="static")
@app.post("/deps/")
async def deps():
    return JSONResponse(file_contents)

@app.post("/deps/{item_id}")
async def create_item( item_id: int):
    return JSONResponse(resp_contents[item_id])

#part  of the  chatbot  api

state = 1
# 0 idle
# 1 initial message  printed 
# 2 department list printed 
# 3 one department controll type printed 
# 4 print consultations  based on controll type
# 5 print controll type of  a department based on consultation 
# 6 missunderstanding while printed  departments need to check message  if it is presented in controll types key words or  in conultation key words
# 7 
#
#
#

def  change_st(State : int):
    state = State;

@app.post("/chatbot/init/")
async def hello_message_resp():
    print(chat_bot_json["hello_mess"])
    # change_st(1) 
    return JSONResponse(chat_bot_json["hello_mess"])

#version with after all parsing


@app.post("/chatbot/{message}")
async def message_resp(message : str):
    if state==1 :
        for i in chat_bot_json["info_vocab"]:
            if (message == str(i)): 
                print(message)
                return JSONResponse(chat_bot_json["link"])
            else:
                print("faggot")

            if(message.find(i)==-1):
                print("faggot")
            else :
                print(message)
                return JSONResponse(chat_bot_json["link"])
        for i in chat_bot_json["department_list_vocab"]:
            if (message == str(i)): 
                print(message)
                return JSONResponse(chat_bot_json["department_list"])
            else:
                print("faggot")

            if(message.find(i)==-1):
                print("faggot")
                # TO_DO:  add  the other cases for this  term
            else :
                print(message)
                change_st(2)
                return JSONResponse(chat_bot_json["department_list"])
        for i in range (1,20):
            if(message == str(resp_contents[i]["short_name"])):
                print(message)
                change_st(1)
                return JSONResponse(resp_contents[i])
                # TO_DO:  add  the other cases for this  term
            else :
                print("faggot2")
            if(message.find(resp_contents[i]["short_name"])==-1):
                print("faggot2")
                if(message==str(i)): 
                    print(message)
                    change_st(1)
                    return JSONResponse(resp_contents[i])
                else:
                    print("Faggot 3 ")
                    

                if(message.find(str(i))==-1): 
                    print("Faggot 3 ")
                else:
                    print(message)
                    change_st(1)
                    return JSONResponse(resp_contents[i])
                # TO_DO:  add  the other cases for this  term
            else :
                print(message)
                change_st(1)
                return JSONResponse(resp_contents[i])
        change_st(3)
        return JSONResponse(chat_bot_json["department_no"])
    return JSONResponse(chat_bot_json["department_no"])

    # if state == 2: 
    #     for i in range (1,20):
    #         if(message == str(resp_contents[i]["short_name"])):
    #             print(message)
    #             change_st(1)
    #             return JSONResponse(resp_contents[i])
    #             # TO_DO:  add  the other cases for this  term
    #         else :
    #             print("faggot2")
    #         if(message.find(resp_contents[i]["short_name"])==-1):
    #             print("faggot2")
    #             if(message==str(i)): 
    #                 print(message)
    #                 change_st(1)
    #                 return JSONResponse(resp_contents[i])
    #             else:
    #                 print("Faggot 3 ")
                    

    #             if(message.find(str(i))==-1): 
    #                 print("Faggot 3 ")
    #             else:
    #                 print(message)
    #                 change_st(1)
    #                 return JSONResponse(resp_contents[i])
    #             # TO_DO:  add  the other cases for this  term
    #         else :
    #             print(message)
    #             change_st(1)
    #             return JSONResponse(resp_contents[i])
    #     change_st(3)
    #     return JSONResponse(chat_bot_json["just_select"])
    # if state == 3: 
    #     for i in chat_bot_json["info_vocab"]:
    #         if (message == str(i)): 
    #             print(message)
    #             return JSONResponse(chat_bot_json["link"])
    #         else:
    #             print("faggot")

    #         if(message.find(i)==-1):
    #             print("faggot")
    #         else :
    #             print(message)
    #             return JSONResponse(chat_bot_json["link"])
    #     change_st(1) 
    #     return JSONResponse(chat_bot_json["just_select"])

