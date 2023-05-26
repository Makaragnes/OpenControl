package com.example.opencontrol.view.viewModel.chatbot

import android.annotation.SuppressLint
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.opencontrol.model.chatbot.MessageItem
import com.example.opencontrol.model.webrtcModels.MessageModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ChatBotViewModel @Inject constructor(

): ViewModel()  {

    //private var listOfMessage = ArrayList<MessageItem>()
    @SuppressLint("MutableCollectionMutableState")
    var somelist = mutableStateListOf<MessageItem>()

}