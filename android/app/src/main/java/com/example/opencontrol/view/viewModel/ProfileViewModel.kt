package com.example.opencontrol.view.viewModel

import android.telephony.mbms.MbmsErrors.InitializationErrors
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.example.opencontrol.model.profileModels.AddressModel
import com.example.opencontrol.model.profileModels.BusinessModel
import com.example.opencontrol.model.profileModels.BusinessModelWithoutToken
import com.example.opencontrol.model.profileModels.DateModel
import com.example.opencontrol.model.profileModels.MainBusinessModel
import com.example.opencontrol.model.profileModels.MainPersonalInfoModel
import com.example.opencontrol.model.profileModels.PassportModel
import com.example.opencontrol.model.profileModels.PersonalInfoModel
import com.example.opencontrol.model.profileModels.PersonalInfoModelWithoutToken
import com.example.opencontrol.model.profileModels.TokenVal
import com.example.opencontrol.obj.DataObj
import com.example.opencontrol.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {
    private val initialEmptyPersonModel = MainPersonalInfoModel(
        PersonalInfoModel(
            personID = 0,
            null,
            null,
            null,
            DateModel(
                null,
                null,
                null,
            ),
            AddressModel(
                null,
                null,
                null,
                null,
                null,
                null,
            ),
            PassportModel(
                null,
                null
            )
        ),
        TokenVal(
            null
        )
    )

    val personState = mutableStateOf<MainPersonalInfoModel>(initialEmptyPersonModel)

    val personID = mutableStateOf(personState.value.person_info.personID)
    val full_name = mutableStateOf(personState.value.person_info.full_name)
    val phone = mutableStateOf(personState.value.person_info.phone)
    val email = mutableStateOf(personState.value.person_info.email)
    val birthday = mutableStateOf(personState.value.person_info.birthday)
    val address = mutableStateOf(personState.value.person_info.address)
    val passport = mutableStateOf(personState.value.person_info.passport)

    private val initialBusinessModel = MainBusinessModel(
        BusinessModel(
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            DateModel(
                null,
                null,
                null,
            ),
            AddressModel(
                null,
                null,
                null,
                null,
                null,
                null,
            ),
            AddressModel(
                null,
                null,
                null,
                null,
                null,
                null,
            ),
        ),
        TokenVal(
            null
        )
    )


    //val businessModel = mutableStateOf<MainBusinessModel>(initialBusinessModel)

    var business = mutableStateOf(
        BusinessModelWithoutToken(
            "",
            "",
            DateModel(
                "",
                "",
                "",
            ),
            "",
            AddressModel(
                "",
                "",
                "",
                "",
                "",
                "",
            ),
            "",
            AddressModel(
                "",
                "",
                "",
                "",
                "",
                "",
            ),
            "",
            "",
            "",
            "",
        )
    )

    var personInfo = mutableStateOf(
        PersonalInfoModelWithoutToken(
            0,
            "",
            "",
            "",
            DateModel(
                "",
                "",
                "",
            ),
            AddressModel(
                "",
                "",
                "",
                "",
                "",
                "",
            ),
            PassportModel(
                "",
                "",
            ),

            )
    )

    val ogrn = mutableStateOf(business.value.OGRN)
    val inn = mutableStateOf(business.value.INN)
    val shortTitle = mutableStateOf(business.value.shortTitle)
    val nameOfTacService = mutableStateOf(business.value.nameOfTaxService)
    val establishedCapital = mutableStateOf(business.value.establishedCapital)
    val infoAboutActivity = mutableStateOf(business.value.infoAboutActivity)
    val additionalActivity = mutableStateOf(business.value.additionalActivity)

    suspend fun getBusinessInfo() {
        business.value = profileRepository.getBusinessInfo().body()!!
        ogrn.value = business.value.OGRN
        inn.value = business.value.INN
        shortTitle.value = business.value.shortTitle
        nameOfTacService.value = business.value.nameOfTaxService
        establishedCapital.value = business.value.establishedCapital
        infoAboutActivity.value = business.value.infoAboutActivity
        additionalActivity.value = business.value.additionalActivity
    }

    suspend fun getPersonInfo() {
        personInfo.value = profileRepository.getPersonInfo().body()!!
        personID.value = personInfo.value.personID
        full_name.value = personInfo.value.full_name
        phone.value = personInfo.value.phone
        email.value = personInfo.value.email
    }

    suspend fun pushPersonInfo() {
        profileRepository.pushPersonInfo(
            MainPersonalInfoModel(
                PersonalInfoModel(
                    personID.value,
                    full_name.value,
                    phone.value,
                    email.value,
                    DateModel(
                        "",
                        "",
                        ""
                    ),
                    AddressModel(
                        "",
                        "",
                        "",
                        "",
                        "",
                        ""
                    ),
                    PassportModel(
                        "",
                        ""
                    ),
                ),
                TokenVal(DataObj.authToken)
            ),
        )
    }

    suspend fun pushBusinessInfo() {
        profileRepository.pushBusinessInfo(
            MainBusinessModel(
                BusinessModel(
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    DateModel(
                        "",
                        "",
                        "",
                    ),
                    AddressModel(
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                    ),
                    AddressModel(
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                    ),
                ),
                TokenVal(DataObj.authToken)
            )
        )
    }
}