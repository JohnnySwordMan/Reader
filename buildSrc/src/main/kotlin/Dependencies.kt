/**
 * @description:
 * @author: geyan
 * @date: 2020-08-14
 */
object Versions {

    const val ANKO_VERSION = "0.10.8"
    const val COROUTINES_ANDROID_VERSION = "1.2.1"
    const val COROUTINES_CORE_VERSION = "1.2.1"
    const val DAGGER_VERSION = "2.20"
    const val KOTLIN_VERSION = "1.3.72"
    const val LOTTIE_VERSION = "2.8.0"
    const val RXJAVA_VERSION = "2.2.10"
    const val RXANDROID_VERSION = "2.1.1"
    const val RETROFIT_VERSION = "2.3.0"

    const val ANDROIDX_APPCOMPAT = "1.0.2"
    const val ANDROIDX_CORE_KTX = "1.0.2"
    const val ANDROIDX_CONSTRAINTLAYOUT = "1.1.3"
    const val LEAKCANARY = "2.2"
    const val ANDROIDX_COORDINATORLAYOUT = "1.0.0"
    const val MATERIAL = "1.0.0"
    const val OKHTTP3_LOGGING_INTERCEPTOR = "3.8.0"
    const val OKHTTP3_OKHTTP = "3.8.0"
    const val LIFECYCLE = "1.1.1"
    const val SLF4J_API = "1.7.21"
    const val SLF4J_SIMPLE = "1.7.21"
    const val JDSJLZX_LRECYCLERVIEW = "1.5.4.3"
    const val AVATAR_IAMGEVIEW = "1.0.3"
    const val GLIDE_VERSION = "4.3.1"
    const val AGENTWEB_VERSION = "4.1.2"
    const val FLEXBOX_VERSION = "2.0.1"
    const val ROUNDED_IAMGEVIEW_VERSION = "2.3.0"
}

object Libs {

    // anko
    const val anko_commons = "org.jetbrains.anko:anko-commons:${Versions.ANKO_VERSION}"
    const val anko_sdk15 = "org.jetbrains.anko:anko-sdk15:${Versions.ANKO_VERSION}"
    const val anko_sdk15_listeners =
        "org.jetbrains.anko:anko-sdk15-listeners:${Versions.ANKO_VERSION}"
    const val anko_sdk15_coroutines =
        "org.jetbrains.anko:anko-sdk15-coroutines:${Versions.ANKO_VERSION}"
    const val anko_appcompat_v7 =
        "org.jetbrains.anko:anko-appcompat-v7:${Versions.ANKO_VERSION}"
    const val anko_appcompat_v7_listeners =
        "org.jetbrains.anko:anko-appcompat-v7-listeners:${Versions.ANKO_VERSION}"

    // coroutines
    const val coroutines_core =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES_CORE_VERSION}"
    const val coroutines_android =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES_ANDROID_VERSION}"

    // dagger
    const val dagger = "com.google.dagger:dagger:${Versions.DAGGER_VERSION}"
    const val dagger_compiler = "com.google.dagger:dagger-compiler:${Versions.DAGGER_VERSION}"
    const val dagger_android = "com.google.dagger:dagger-android:${Versions.DAGGER_VERSION}"
    const val dagger_android_support =
        "com.google.dagger:dagger-android-support:${Versions.DAGGER_VERSION}"
    const val dagger_android_processor =
        "com.google.dagger:dagger-android-processor:${Versions.DAGGER_VERSION}"

    // kotlin
    const val kotlin_stdlib_jdk7 =
        "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.KOTLIN_VERSION}"
    const val kotlin_reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.KOTLIN_VERSION}"
    const val kotlin_gradle_plugin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN_VERSION}"
    const val kotlin_noarg = "org.jetbrains.kotlin:kotlin-noarg:${Versions.KOTLIN_VERSION}"

    // rx
    const val rxjava = "io.reactivex.rxjava2:rxjava:${Versions.RXJAVA_VERSION}"
    const val rxandroid = "io.reactivex.rxjava2:rxandroid:${Versions.RXANDROID_VERSION}"

    // retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT_VERSION}"
    const val retrofit_adapter_rxjava2 =
        "com.squareup.retrofit2:adapter-rxjava2:${Versions.RETROFIT_VERSION}"
    const val retrofit_converter_gson =
        "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT_VERSION}"

    // lottie
    const val lottie = "com.airbnb.android:lottie:${Versions.LOTTIE_VERSION}"

    // androidx
    const val androidx_appcompat = "androidx.appcompat:appcompat:${Versions.ANDROIDX_APPCOMPAT}"
    const val androidx_core_ktx = "androidx.core:core-ktx:${Versions.ANDROIDX_CORE_KTX}"
    const val androidx_constraintlayout =
        "androidx.constraintlayout:constraintlayout:${Versions.ANDROIDX_CONSTRAINTLAYOUT}"
    const val androidx_coordinatorlayout =
        "androidx.coordinatorlayout:coordinatorlayout:${Versions.ANDROIDX_COORDINATORLAYOUT}"

    const val material = "com.google.android.material:material:${Versions.MATERIAL}"

    // leakcanary
    const val leakcanary = "com.squareup.leakcanary:leakcanary-android:${Versions.LEAKCANARY}"

    const val okhttp3_logging_interceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP3_LOGGING_INTERCEPTOR}"
    const val okhttp3_okhttp = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP3_OKHTTP}"

    const val lifecycle = "android.arch.lifecycle:extensions:${Versions.LIFECYCLE}"

    const val slf4j_api = "org.slf4j:slf4j-api:${Versions.SLF4J_API}"
    const val slf4j_simple = "org.slf4j:slf4j-simple:${Versions.SLF4J_SIMPLE}"


    const val jdsjlzx_lrecyclerview =
        "com.github.jdsjlzx:LRecyclerView:${Versions.JDSJLZX_LRECYCLERVIEW}"
    const val avatarImageView = "cn.carbs.android:AvatarImageView:${Versions.AVATAR_IAMGEVIEW}"

    const val glide = "com.github.bumptech.glide:glide:${Versions.GLIDE_VERSION}"
    const val glide_compiler = "com.github.bumptech.glide:compiler:${Versions.GLIDE_VERSION}"

    const val agentweb = "com.just.agentweb:agentweb:${Versions.AGENTWEB_VERSION}"

    const val flexbox = "com.google.android:flexbox:${Versions.FLEXBOX_VERSION}"

    const val roundedimageview = "com.makeramen:roundedimageview:${Versions.ROUNDED_IAMGEVIEW_VERSION}"

}