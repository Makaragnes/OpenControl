package com.example.opencontrol.model.profileModels

data class MainBusinessModel(
    val business_info: BusinessModel,
    val token: TokenVal?,
)
data class BusinessModel(
    val OGRN: String?,
    val fullTitle: String?,
    val INN: String?,
    val establishedCapital: String?,
    val infoAboutActivity: String?,
    val additionalActivity: String?,
    val nameOfTaxService: String?,
    val shortTitle: String?,
    val dataUGRUL: DateModel?,
    val businessLocation: AddressModel?,
    val taxLocation: AddressModel?,

)

data class TokenVal(
    val token_val: String?
)

data class BusinessModelWithoutToken(
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
    val additionalActivity: String?,
)


