package com.berakahnd.notebook.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.berakahnd.notebook.ui.viewmodel.MainViewModel

@Composable
fun AppNavHost(
    mainViewModel : MainViewModel,
    navController: NavHostController,
    startDestination: String = NavigationItem.Main.route,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(NavigationItem.Main.route) {
            MainScreen(mainViewModel = mainViewModel, onUpsetClick = {
                navController.navigate(Screen.UPSET.name)
            })
        }
        composable(NavigationItem.Upset.route) {
            UpsetScreen(mainViewModel = mainViewModel, onBackClick = {
                navController.popBackStack()
            })
        }
    }
}
enum class Screen {
    MAIN,
    UPSET,
}
sealed class NavigationItem(val route: String) {
    object Main : NavigationItem(Screen.MAIN.name)
    object Upset : NavigationItem(Screen.UPSET.name)
}