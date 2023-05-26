package com.example.opencontrol.model.chatbot

data class MessageItem(
    var message: String,
    var time: String,
    val whereAreYouFrom: Boolean    // false if outside/ true if inside
)
