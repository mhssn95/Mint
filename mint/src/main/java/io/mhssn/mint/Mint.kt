package io.mhssn.mint

import com.google.gson.Gson
import io.mhssn.annotations.Key
import io.mhssn.common.Utils
import io.mhssn.mint.store.Store
import java.lang.IllegalArgumentException
import java.lang.reflect.Method
import java.lang.reflect.Proxy
import kotlin.reflect.KClass
import kotlin.reflect.KClassifier
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.memberExtensionFunctions

@Suppress("UNCHECKED_CAST")
class Mint(private val store: Store) {

    private val gson = Gson()

    fun <T : Any> create(kClass: KClass<T>): T {
        return Proxy.newProxyInstance(
            kClass.java.classLoader,
            arrayOf(kClass.java)
        ) { _, method, args ->
            val fields = kClass.declaredMemberProperties.map { it.name to it.returnType.classifier }
            val type = findType(method, fields)
            when (type) {
                String::class -> {
                    invoke(method.name, {
                        store.setString(method.getKey(), args.first() as String)
                    }, {
                        store.getString(method.getKey())
                    })
                }
                Int::class.javaPrimitiveType -> {
                    invoke(method.name, {
                        store.setInt(method.getKey(), args.first() as Int)
                    }, {
                        store.getInt(method.getKey())
                    })
                }
                Float::class.javaPrimitiveType -> {
                    invoke(method.name, {
                        store.setFloat(method.getKey(), args.first() as Float)
                    }, {
                        store.getFloat(method.getKey())
                    })
                }
                Boolean::class.javaPrimitiveType -> {
                    invoke(method.name, {
                        store.setBoolean(method.getKey(), args.first() as Boolean)
                    }, {
                        store.getBoolean(method.getKey())
                    })
                }
                Long::class.javaPrimitiveType -> {
                    invoke(method.name, {
                        store.setLong(method.getKey(), args.first() as Long)
                    }, {
                        store.getLong(method.getKey())
                    })
                }
                Double::class.javaPrimitiveType -> {
                    invoke(method.name, {
                        store.setDouble(method.getKey(), args.first() as Double)
                    }, {
                        store.getDouble(method.getKey())
                    })
                }
                else -> {
                    invoke(method.name, {
                        store.setString(method.getKey(), gson.toJson(args.first()))
                    }, {
                        gson.fromJson(store.getString(method.getKey()), type)
                    })
                }
            }
        } as T
    }

    private fun <R> invoke(method: String, setter: () -> Unit, getter: () -> R): Any? {
        return when {
            Utils.isSetter(method) -> {
                setter()
            }
            Utils.isGetter(method) -> {
                getter()
            }
            else -> {
                throw IllegalArgumentException("only setter and getter accepted")
            }
        }
    }

    private fun findType(method: Method, fields: List<Pair<String, KClassifier?>>): Class<*> {
        return if (method.returnType == Void.TYPE) {
            fields.find { it.first == Utils.getFieldName(method.name) }?.second?.let { (it as KClass<*>).java }
                ?: throw IllegalArgumentException("Field not found")
        } else {
            method.returnType
        }
    }

    private fun Method.getKey(): String {
        return Utils.getKey(
            Utils.getFieldName(name),
            annotations.find {
                it is Key
            }?.let {
                it as Key
            }?.key
        )
    }
}