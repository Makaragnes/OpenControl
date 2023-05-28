package com.example.opencontrol.view.viewModel

import android.telephony.mbms.MbmsErrors.InitializationErrors
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.example.opencontrol.model.profileModels.BusinessModel
import com.example.opencontrol.model.profileModels.PersonalInfoModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(

): ViewModel() {
    private val initialEmptyPersonModel = PersonalInfoModel(
        personID = 0,
        null,
        null,
        null,
        null,
        null,
        null,
        )
    val personState = mutableStateOf<PersonalInfoModel>(initialEmptyPersonModel)

    val full_name = mutableStateOf(personState.value.full_name)
    val phone = mutableStateOf(personState.value.phone)
    val email = mutableStateOf(personState.value.email)
    val birthday = mutableStateOf(personState.value.birthday)
    val address = mutableStateOf(personState.value.address)
    val passport = mutableStateOf(personState.value.passport)

    private val initialBusinessModel = BusinessModel(
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
    )
    val businessModel = mutableStateOf<BusinessModel>(initialBusinessModel)

    val ogrn = mutableStateOf(businessModel.value.OGRN)
    val inn = mutableStateOf(businessModel.value.INN)
    val shortTitle = mutableStateOf(businessModel.value.shortTitle)
    val nameOfTacService = mutableStateOf(businessModel.value.nameOfTaxService)
    val establishedCapital = mutableStateOf(businessModel.value.establishedCapital)
    val infoAboutActivity = mutableStateOf(businessModel.value.infoAboutActivity)
    val additionalActivity = mutableStateOf(businessModel.value.additionalActivity)
}