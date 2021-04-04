package io.mhssn.mint.Store

import io.mhssn.annotations.Mint

@Mint
interface AppValues {

    var stringStore: String

    var intStore: Int

    var floatStore: Float

    var doubleStore: Double

    var booleanStore: Boolean

    var user: User?

}