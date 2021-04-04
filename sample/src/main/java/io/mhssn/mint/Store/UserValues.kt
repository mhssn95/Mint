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

}