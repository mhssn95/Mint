package io.mhssn.mint

import org.junit.Test

class MintUnitTest {

    private val store = TestStore()
    private val testValues = Mint(store).create(TestValues::class)

    @Test
    fun testStoreString() {
        val value = Utils.getRandomString(20)
        testValues.testString = value
        val storedValues = testValues.testString
        assert(value == storedValues)
    }

    @Test
    fun testStoreBoolean() {
        val value = Utils.getRandomBoolean()
        testValues.testBoolean = value
        val storedValue = testValues.testBoolean
        assert(value == storedValue)
    }

    @Test
    fun testStoreInt() {
        val value = Utils.getRandomInt()
        testValues.testInt = value
        val storedValue= testValues.testInt
        assert(value == storedValue)
    }

    @Test
    fun testStoreFloat() {
        val value = Utils.getRandomFloat()
        testValues.testFloat = value
        val storedValue = testValues.testFloat
        assert(value == storedValue)
    }

    @Test
    fun testStoreDouble() {
        val value = Utils.getRandomDouble()
        testValues.testDouble = value
        val storedValue = testValues.testDouble
        assert(value == storedValue)
    }

    @Test
    fun testStoreLong() {
        val value = Utils.getRandomLong()
        testValues.testLong = value
        val storedValue = testValues.testLong
        assert(value == storedValue)
    }

    @Test
    fun testStoreObject() {
        val value = TestModel(Utils.getRandomString(20), Utils.getRandomInt())
        testValues.testObject = value
        val storedValue = testValues.testObject
        assert(storedValue == value)
    }

    data class TestModel(val name: String, val age: Int)
}