object Build {
    private const val androidBuildToolsVersion = "8.0.0"
    const val androidBuildTools = "com.android.tools.build:gradle:$androidBuildToolsVersion"

    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.version}"

    private const val hiltAndroidGradlePluginVersion = "2.38.1"
    const val hiltAndroidGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$hiltAndroidGradlePluginVersion"

    private const val desugarVersion  ="2.0.3"
    const val desugarJdk = "com.android.tools:desugar_jdk_libs:$desugarVersion"
}