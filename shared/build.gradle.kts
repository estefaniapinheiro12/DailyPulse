plugins {
    // Plugin KMP
    alias(libs.plugins.kotlinMultiplatform)
    // Plugin da Biblioteca Android
    alias(libs.plugins.androidLibrary)
    id("co.touchlab.skie") version "0.4.19"
    // Serialização de plugin
    kotlin("plugin.serialization") version "1.9.20"
    // Plugin do Sql Delight
    alias(libs.plugins.sqlDelight)

}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        // Conjunto de fontes juntamente com suas dependências
        commonMain.dependencies {
            //put your multiplatform dependencies here
            // dependência das corroutinas do kotlin (forma de lidar com código assíncrono
            implementation(libs.kotlinx.coroutines.core)
            // núcleo do clinte
            implementation(libs.ktor.client.core)
            // negocição de conteúdo do cliente
            implementation(libs.ktor.client.content.negotiation)
            // serialização Kotlin Json
            implementation(libs.ktor.serialization.kotlinx.json)

            implementation(libs.kotlinx.datetime)
            implementation(libs.koin.core)
            // Banco de dados SQl
            implementation(libs.sql.coroutines.extensions)

        }
        // as dependências(bibliotecas) do Android
       androidMain.dependencies {
           // Depedência do modelo de exibição (Android ViewModel)
           implementation(libs.androidx.lifecycle.viewmodel.ktx)
           implementation(libs.ktor.client.android)
           // banco de dados SQLem p
           implementation(libs.sql.android.driver)

       }
        // dependências (bibliotecas) do ios main
       iosMain.dependencies {
           implementation(libs.ktor.client.darwin)
           implementation(libs.sql.native.driver)
       }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
            // Banco de dados SQL
        }
    }
}

android {
    namespace = "com.estefaniapinheiro.dailypulse"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
sqldelight {
    databases {
        create(name = "DailyPulseDatabase"){
            packageName.set("estefaniapinheiro.dailypulse.db")
        }
    }
}