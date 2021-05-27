package io.mhssn.processor

import com.google.auto.service.AutoService
import com.squareup.kotlinpoet.*
import io.mhssn.mint.annotations.Delete
import io.mhssn.mint.annotations.Key
import io.mhssn.mint.annotations.Mint
import io.mhssn.mint.utils.Utils
import java.io.File
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind
import javax.lang.model.element.ExecutableElement
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

@AutoService(Processor::class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes("*")
@SupportedOptions(MintProcessor.KAPT_GENERATED_OPTION_NAME)
class MintProcessor : AbstractProcessor() {

    companion object {
        const val KAPT_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
    }

    override fun process(
        annotations: MutableSet<out TypeElement>,
        roundEnv: RoundEnvironment
    ): Boolean {
        roundEnv.getElementsAnnotatedWith(Mint::class.java).forEach { classElement ->
            if (classElement.kind != ElementKind.INTERFACE) {
                processingEnv.printError("Only interfaces acceptable")
                return false
            }
            generateInterface(classElement)
        }
        return false
    }

    private fun generateInterface(element: Element) {
        val path: String = processingEnv.options[KAPT_GENERATED_OPTION_NAME].orEmpty()
        if (path.isEmpty()) {
            processingEnv.printError("can't find the generate path")
            return
        }
        val packageName = element.getPackageName()
        val interfaceName = "${element.simpleName}Mint"
        processingEnv.print(packageName)
        val fields = element.enclosedElements.filter { it.kind == ElementKind.METHOD }.map {
            (it as ExecutableElement).let { executableElement ->
                Property(
                    Utils.getFieldName(it.simpleName.toString()),
                    executableElement.returnType,
                    executableElement.getKey()
                )
            }
        }.distinctBy { it.name }

        val generated = TypeSpec.interfaceBuilder(interfaceName).apply {
            fields.forEach {
                this.addProperty(getProperty(it))
                this.addFunction(getDeleteFunction(Utils.appendFirst("delete", it.name), it))
            }
        }.build()

        val file = File(path).apply { if (!exists()) mkdir() }
        FileSpec.builder(packageName, interfaceName)
            .addType(generated)
            .build()
            .writeTo(file)
    }

    private fun getProperty(property: Property): PropertySpec {
        return PropertySpec.builder(property.name, String::class).apply {
            mutable(true)
            property.key?.let { key ->
                addAnnotation(getKeyAnnotation(key))
            }
        }.build()
    }

    private fun getFunction(name: String, property: Property): FunSpec {
        return FunSpec.builder(name).apply {
            property.key?.let { key ->
                addAnnotation(getKeyAnnotation(key))
            }
        }
            .addModifiers(KModifier.ABSTRACT)
            .build()
    }

    private fun getDeleteFunction(name: String, property: Property): FunSpec {
        return FunSpec.builder(name).apply {
            property.key?.let { key ->
                addAnnotation(getDeleteAnnotation(key))
            }
        }
            .addModifiers(KModifier.ABSTRACT)
            .build()
    }

    private fun Element.getPackageName(): String {
        return processingEnv.elementUtils.getPackageOf(this).toString()
    }

    private fun ExecutableElement.getKey(): String {
        return Utils.getKey(
            Utils.getFieldName(simpleName.toString()),
            getAnnotation(Key::class.java)?.key
        )
    }

    private fun getKeyAnnotation(key: String): AnnotationSpec {
        return AnnotationSpec
            .builder(Key::class)
            .addMember("key = %S", key)
            .build()
    }

    private fun getDeleteAnnotation(key: String): AnnotationSpec {
        return AnnotationSpec
            .builder(Delete::class)
            .addMember("key = %S", key)
            .build()
    }

    private fun ProcessingEnvironment.print(out: String) {
        messager.printMessage(Diagnostic.Kind.NOTE, out)
    }

    private fun ProcessingEnvironment.printError(out: String) {
        messager.printMessage(Diagnostic.Kind.ERROR, out)
    }
}