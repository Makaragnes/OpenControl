package com.example.opencontrol.model.profileModels

import java.time.Month

data class PersonalInfoModel(
    val personID: Int,
    val full_name: String?,
    val phone: String?,
    val email: String?,
    val birthday: DateModel?,
    val address: AddressModel?,
    val passport: PassportModel?
)

data class DateModel(
    val day: Int?,
    val month: String?,
    val year: Int?
)

data class AddressModel(
    val postIndex: Int?,
    val country: String?,
    val city: String?,
    val street: String?,
    val house: String?,
    val flat: String?
)

data class PassportModel(
    val number: Int?,
    val series: Int?
)
