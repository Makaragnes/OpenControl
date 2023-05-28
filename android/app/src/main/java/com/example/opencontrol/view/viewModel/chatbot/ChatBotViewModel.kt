package com.example.opencontrol.view.viewModel.chatbot

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.opencontrol.model.OneStrModel
import com.example.opencontrol.model.chatbot.MessageItem
import com.example.opencontrol.model.webrtcModels.MessageModel
import com.example.opencontrol.repository.ChatBotRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ChatBotViewModel @Inject constructor(
    private val chatBotRepository: ChatBotRepository
): ViewModel()  {

    //private var listOfMessage = ArrayList<MessageItem>()
    @SuppressLint("MutableCollectionMutableState")
    var somelist = mutableStateListOf<MessageItem>()
    val returnMessage = mutableStateOf<OneStrModel>(OneStrModel(""))

    suspend fun getInit(){
        somelist.add(
            MessageItem(
                chatBotRepository.getInit().body()?.message!!,
                "sdf",
                true
                )
        )
    }

    suspend fun sendMessage(){
        returnMessage.value = OneStrModel(chatBotRepository.sendMessage().body()!!.message)
        Log.d("HHH", returnMessage.value.message)
    }

}