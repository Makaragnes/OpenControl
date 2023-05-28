package com.example.opencontrol.model.profileModels

data class BusinessModel(
    val OGRN: String?,
    val fullTitle: String?,
    val dataUGRUL: DateModel?,
    val shortTitle: String?,
    val businessLocation: AddressModel?,
    val nameOfTaxService: String?,
    val taxLocation: AddressModel?,
    val INN: String?,
    val establishedCapital: String?,
    val infoAboutActivity: String?,
    val additionalActivity: String?

)


