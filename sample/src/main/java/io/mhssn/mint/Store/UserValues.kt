package io.mhssn.mint.Store

import io.mhssn.annotations.Key
import io.mhssn.annotations.Mint

@Mint
interface UserValues {

    @Key("pref_username")
    var username: String

    @Key("pref_age")
    var age: String

    @Key("pref_gender")
    var gender: String

    var isAdmin: Boolean

    var healthInfo: HealthInfo

}

data class HealthInfo(
    val bloodType: String,
    val weight: Float,
    val height: Float
)