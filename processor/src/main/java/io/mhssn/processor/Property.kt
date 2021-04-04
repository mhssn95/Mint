package io.mhssn.processor

import javax.lang.model.element.AnnotationMirror
import javax.lang.model.type.TypeMirror

data class Property(val name: String, val type: TypeMirror, val key: String?)