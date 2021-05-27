package io.mhssn.mint.annotations

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Delete(val key: String)