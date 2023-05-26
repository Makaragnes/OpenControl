package com.example.opencontrol.view.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(

): ViewModel() {

    val availableServer = mutableStateOf(true)
}