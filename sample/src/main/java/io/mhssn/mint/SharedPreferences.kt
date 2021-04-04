package io.mhssn.mint

import android.content.Context
import io.mhssn.mint.store.Store

class SharedPreferences(context: Context) : Store {

    companion object {
        private const val sharedPref = "SharedPref"
    }

    private val sharedPreferences = context.getSharedPreferences(
        sharedPref,
        Context.MODE_PRIVATE
    )

    override fun setString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    override fun getString(key: String, default: String?): String? {
        return sharedPreferences.getString(key, default)
    }

    override fun setInt(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    override fun getInt(key: String, default: Int): Int {
        return sharedPreferences.getInt(key, default)
    }

    override fun setBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    override fun getBoolean(key: String, default: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, default)
    }

    override fun setFloat(key: String, value: Float) {
        sharedPreferences.edit().putFloat(key, value).apply()
    }

    override fun getFloat(key: String, default: Float): Float? {
        return sharedPreferences.getFloat(key, default)
    }

    override fun setDouble(key: String, value: Double) {
        sharedPreferences.edit().putString(key, value.toString()).apply()
    }

    override fun getDouble(key: String, default: Double): Double {
        return sharedPreferences.getString(key, default.toString())!!.toDouble()
    }

    override fun setLong(key: String, value: Long) {
        sharedPreferences.edit().putLong(key, value).apply()
    }

    override fun getLong(key: String, default: Long): Long {
        return sharedPreferences.getLong(key, default)
    }

    override fun delete(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }
}