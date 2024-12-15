package com.estefaniapinheiro.dailypulse.android

import android.app.Application
import com.estefaniapinheiro.dailypulse.android.di.databaseModule
import com.estefaniapinheiro.dailypulse.android.di.viewModelsModule
import com.estefaniapinheiro.dailypulse.di.sharedKoinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

// A classe que une todas as peças, a classe do aplicativo, é a primeira classe que está sendo instanciada
// quando abrimos o aplicativo, essa classe vai permancer viva até que o usuário encerre o app
class DailyPulseApp: Application (){

    override fun onCreate() {
        super.onCreate()
        // Inicializando o koin
        initKoin()
    }
    private fun initKoin(){
        // todos os modulos que participarão do gráfico de dependências
        val modules = sharedKoinModules + viewModelsModule + databaseModule
        // bloco Koin inicial
        startKoin {
            // passamos o AndroidContext, pq às vezes precisamos incializar dependências que dependem
            // do contexto do Android
            androidContext(this@DailyPulseApp)
            // módulos com o gráfico de dependência, que são os modulos que reunimos acima
            modules(modules)

        }
    }
}