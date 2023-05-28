package com.example.opencontrol.model.profileModels

import java.time.Month

data class MainPersonalInfoModel(
    val person_info: PersonalInfoModel,
    val token: TokenVal?
)
data class PersonalInfoModel(
    val personID: Int,
    val full_name: String?,
    val phone: String?,
    val email: String?,
    val birthday: DateModel?,
    val address: AddressModel?,
    val passport: PassportModel?,
)

data class DateModel(
    val day: String?,
    val month: String?,
    val year: String?
)

data class AddressModel(
    val postIndex: String?,
    val country: String?,
    val city: String?,
    val street: String?,
    val house: String?,
    val flat: String?
)

data class PassportModel(
    val number: String?,
    val series: String?
)

data class PersonalInfoModelWithoutToken(
    val personID: Int,
    val full_name: String?,
    val phone: String?,
    val email: String?,
    val birthday: DateModel?,
    val address: AddressModel?,
    val passport: PassportModel?,
)
