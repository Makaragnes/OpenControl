package com.example.opencontrol.utilits

import com.example.opencontrol.model.webrtcModels.MessageModel

interface NewMessageInterface {
    fun onNewMessage(message: MessageModel)
}