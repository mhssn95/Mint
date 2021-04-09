package io.mhssn.mint

import io.mhssn.annotations.Mint

@Mint
interface TestValues {

    var testString: String

    var testInt: Int

    var testBoolean: Boolean

    var testFloat: Float

    var testDouble: Double

    var testLong: Long

    var testObject: MintUnitTest.TestModel

}