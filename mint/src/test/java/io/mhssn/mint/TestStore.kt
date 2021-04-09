package io.mhssn.mint

import io.mhssn.mint.store.Store

class TestStore: Store {
    private val keyValueStore = HashMap<String, Any>()

    override fun setString(key: String, value: String) {
        keyValueStore[key] = value
    }

    override fun getString(key: String, default: String?): String? {
        return keyValueStore[key] as String?
    }

    override fun setInt(key: String, value: Int) {
        keyValueStore[key] = value
    }

    override fun getInt(key: String, default: Int): Int {
        return keyValueStore[key] as Int
    }

    override fun setBoolean(key: String, value: Boolean) {
        keyValueStore[key] = value
    }

    override fun getBoolean(key: String, default: Boolean): Boolean {
        return keyValueStore[key] as Boolean
    }

    override fun setFloat(key: String, value: Float) {
        keyValueStore[key] = value
    }

    override fun getFloat(key: String, default: Float): Float {
        return keyValueStore[key] as Float
    }

    override fun setDouble(key: String, value: Double) {
        keyValueStore[key] = value
    }

    override fun getDouble(key: String, default: Double): Double {
        return keyValueStore[key] as Double
    }

    override fun setLong(key: String, value: Long) {
        keyValueStore[key] = value
    }

    override fun getLong(key: String, default: Long): Long {
        return keyValueStore[key] as Long
    }

    override fun delete(key: String) {
        keyValueStore.remove(key)
    }
}