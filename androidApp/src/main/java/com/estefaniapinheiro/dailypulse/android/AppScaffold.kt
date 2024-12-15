package com.estefaniapinheiro.dailypulse.android

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.estefaniapinheiro.dailypulse.android.screens.AboutScreen
import com.estefaniapinheiro.dailypulse.android.screens.ArticlesScreen
import com.estefaniapinheiro.dailypulse.android.screens.Screens

@Composable
// Marcado como "Composable" pois faz parte da interface so usuário
fun AppScaffold() {
    val navController = rememberNavController()

    Scaffold {
        AppNavHost(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
        )
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        // Destino inicial = tela de artigo
        startDestination = Screens.ARTICLES.route,
        modifier = modifier,
    ) {
        composable(Screens.ARTICLES.route) {
            ArticlesScreen(
                // Função de retorno de chamada real
                // navController = sendo usado navegar "navigate" até a tela sobre o dispostivo "ABOUT_DEVICE"
                // Ação que ocorrerá no clique
                onAboutButtonClick = { navController.navigate(Screens.ABOUT_DEVICE.route) },

            )
        }
        // Tela sobre
        composable(Screens.ABOUT_DEVICE.route) {
            AboutScreen(
                // retorno da chamada quando o usuário clicar no botão para cima
                // Que abrirá a pilha traseira no dosso controlador de navegação "navController", o que,
                // destruirá a tela, e veremos novamente a tela atrás que será a tela de artigo
                onUpButtonClick = { navController.popBackStack() }
            )
        }
    }
}