package com.example.opencontrol.model.webrtcModels

data class IceCandidateModel(
    val sdpMid:String,
    val sdpMLineIndex:Double,
    val sdpCandidate:String
)