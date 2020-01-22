
object Versions {

    const val minSdkVersion = 23
    const val targetSdkVersion = 28
    const val versionCode = 1
    const val versionName = "1.0"

    const val kotlin = "1.3.61"
    const val appCompat = "1.1.0"
    const val coreKtx = "1.1.0"
    const val constraintLayout = "1.1.3"
    const val lifecycleExtensions = "2.2.0-rc03"
    const val lifecycleViewModelKtx = "2.1.0"
    const val lifecycleSavedState = "1.0.0-rc03"
    const val activityExtensions = "1.0.0"
    const val assistedInjection = "0.3.2-rc01"
    const val savedState = "1.0.0"
    const val material = "1.0.0"
    const val rxJava = "2.1.12"
    const val rxJavaAndroid = "2.0.1"
    const val dagger = "2.19"
    const val retrofit = "2.3.0"
    const val loggingInterceptor = "3.8.0"
    const val retrofitGsonConverter = "2.3.0"
    const val picasso = "2.71828"
    const val junit = "4.12"
    const val mockito = "3.1.0"
    const val mockitoKotlin = "2.2.0"
    const val junitExt = "1.1.1"
    const val espresso = "3.2.0"
    const val archComponentsTest= "1.1.0"

}

object Dependencies{

    const val kotlinStd = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val gradle = "com.android.tools.build:gradle:3.5.3"

    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleExtensions}"
    const val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleViewModelKtx}"
    const val lifecycleSavedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycleSavedState}"

    const val activityExtensions = "androidx.activity:activity-ktx:${Versions.activityExtensions}"
    const val assistedInjection = "com.vikingsen.inject:viewmodel-inject:${Versions.assistedInjection}"
    const val assistedInjectionProcessor = "com.vikingsen.inject:viewmodel-inject-processor:${Versions.assistedInjection}"
    const val savedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.savedState}"
    const val material = "com.google.android.material:material:${Versions.material}"

    const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
    const val rxJavaRetrofitAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    const val rxJavaAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxJavaAndroid}"

    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerAndroid = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    const val daggerSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val daggerProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"
    const val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofitGsonConverter}"

    const val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"

    const val junit = "junit:junit:${Versions.junit}"
    const val mockito = "org.mockito:mockito-core:${Versions.mockito}"
    const val mockitoAndroid = "org.mockito:mockito-android:${Versions.mockito}"
    const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"
    const val junitExt = "androidx.test.ext:junit:${Versions.junitExt}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val archComponentsTest = "android.arch.core:core-testing:${Versions.archComponentsTest}"
}