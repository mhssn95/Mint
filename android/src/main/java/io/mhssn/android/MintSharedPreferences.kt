package io.mhssn.android

import android.content.SharedPreferences
import io.mhssn.mint.store.Store

class MintSharedPreferences(private val prefs: SharedPreferences) : Store {

    override fun setString(key: String, value: String) {
        prefs.edit().putString(key, value).apply()
    }

    override fun getString(key: String, default: String?): String? {
        return prefs.getString(key, default)
    }

    override fun setInt(key: String, value: Int) {
        prefs.edit().putInt(key, value).apply()
    }

    override fun getInt(key: String, default: Int): Int {
        return prefs.getInt(key, default)
    }

    override fun setBoolean(key: String, value: Boolean) {
        prefs.edit().putBoolean(key, value).apply()
    }

    override fun getBoolean(key: String, default: Boolean): Boolean {
        return prefs.getBoolean(key, default)
    }

    override fun setFloat(key: String, value: Float) {
        prefs.edit().putFloat(key, value).apply()
    }

    override fun getFloat(key: String, default: Float): Float {
        return prefs.getFloat(key, default)
    }

    override fun setDouble(key: String, value: Double) {
        prefs.edit().putString(key, value.toString()).apply()
    }

    override fun getDouble(key: String, default: Double): Double {
        return prefs.getString(key, default.toString())!!.toDouble()
    }

    override fun setLong(key: String, value: Long) {
        prefs.edit().putLong(key, value).apply()
    }

    override fun getLong(key: String, default: Long): Long {
        return prefs.getLong(key, default)
    }

    override fun delete(key: String) {
        prefs.edit().remove(key).apply()
    }
}