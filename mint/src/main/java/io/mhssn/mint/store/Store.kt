package io.mhssn.mint.store

import com.google.gson.Gson

interface Store {

    fun setString(key: String, value: String)

    fun getString(key: String, default: String? = null): String?

    fun setInt(key: String, value: Int)

    fun getInt(key: String, default: Int = -1): Int

    fun setBoolean(key: String, value: Boolean)

    fun getBoolean(key: String, default: Boolean = false): Boolean

    fun setFloat(key: String, value: Float)

    fun getFloat(key: String, default: Float = -1f): Float

    fun setDouble(key: String, value: Double)

    fun getDouble(key: String, default: Double = -1.0): Double

    fun setLong(key: String, value: Long)

    fun getLong(key: String, default: Long = -1L): Long

    fun setObject(key: String, `object`: Any) {
        val gson = Gson().toJson(`object`)
        setString(key, gson)
    }

    fun <T> getObject(key: String, `class`: Class<T>, default: Any? = null): T {
        val `object` = getString(key)?.let { Gson().fromJson(it, `class`) }?: default
        return `object` as T
    }

    fun delete(key: String)

}