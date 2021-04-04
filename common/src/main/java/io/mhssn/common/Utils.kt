package io.mhssn.common

object Utils {
    fun getFieldName(method: String): String {
        return method.drop(3).replaceRange(
            0,
            1,
            method[3].toLowerCase().toString()
        )
    }

    fun isSetter(method: String): Boolean {
        return method.startsWith("set")
    }

    fun isGetter(method: String): Boolean {
        return method.startsWith("get")
    }

    fun appendFirst(input: String, field: String): String {
        return input + upperCaseFirstChar(field)
    }

    fun getKey(propertyName: String, key: String?): String {
        return key?:"pref_$propertyName"
    }

    private fun upperCaseFirstChar(input: String): String {
        return input.replaceRange(0, 1, input[0].toUpperCase().toString())
    }
}