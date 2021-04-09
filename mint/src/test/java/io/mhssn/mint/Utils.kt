package io.mhssn.mint

import kotlin.random.Random

object Utils {

    fun getRandomString(length: Int): String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

    fun getRandomBoolean(): Boolean {
        return Random.nextBoolean()
    }

    fun getRandomInt(): Int {
        return Random.nextInt()
    }

    fun getRandomFloat(): Float {
        return Random.nextFloat()
    }

    fun getRandomDouble(): Double {
        return Random.nextDouble()
    }

    fun getRandomLong(): Long {
        return Random.nextLong()
    }

}