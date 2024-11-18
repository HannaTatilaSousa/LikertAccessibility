package com.example.likertaccessibility

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.likertaccessibility.ui.theme.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MyApp() }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreenNames.MAIN_SCREEN) {
        composable(ScreenNames.MAIN_SCREEN) { MainScreenRoute(navController) }
        composable(ScreenNames.LINKER_WITHOUT_ACCESSIBILITY) {
            LikertWithoutAccessibilityRoute(
                navController
            )
        }
        composable(ScreenNames.LINKER_WITH_ACCESSIBILITY) {
            LikertWithAccessibilityRoute(
                navController
            )
        }
    }
}

@Composable
fun MainScreenRoute(navController: NavHostController) {
    MainScreen(
        onButtonClickWithoutAccessibility = {
            navController.navigate(ScreenNames.LINKER_WITHOUT_ACCESSIBILITY)
        },
        onButtonClickWithAccessibility = {
            navController.navigate(ScreenNames.LINKER_WITH_ACCESSIBILITY)
        },
    )
}

@Composable
fun LikertWithoutAccessibilityRoute(navController: NavHostController) {
    LikertWithoutAccessibilityScreen {
        navController.popBackStack(
            ScreenNames.MAIN_SCREEN,
            inclusive = false
        )
    }
}

@Composable
fun LikertWithAccessibilityRoute(navController: NavHostController) {
    LikertWithAccessibilityScreen {
        navController.popBackStack(
            ScreenNames.MAIN_SCREEN,
            inclusive = false
        )
    }
}