package com.template

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.template.ui.theme.FirstScreen
import com.template.ui.theme.SecondScreen
import com.template.ui.theme.SlotsTheme
import com.template.ui.theme.ThirdScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            SlotsTheme {
                NavHost(navController = navController, startDestination = "FirstScreen") {
                    composable("FirstScreen") { FirstScreen(navController = navController, coins = 1000) }
                    composable("ThirdScreen/{coins}",
                        arguments = listOf(navArgument("coins") { type = NavType.IntType })
                    ) {
                        ThirdScreen(
                            navController = navController,
                            coins = it.arguments?.getInt("coins")
                        )
                    }
                    composable(
                        "FirstScreen/{coins}",
                        arguments = listOf(navArgument("coins") { type = NavType.IntType })
                    ) {
                        FirstScreen(
                            navController = navController,
                            coins = it.arguments?.getInt("coins")
                        )
                    }
                    composable(
                        "SecondScreen/{coins}",
                        arguments = listOf(navArgument("coins") { type = NavType.IntType })
                    ) {
                        SecondScreen(
                            navController = navController,
                            coins = it.arguments?.getInt("coins")
                        )
                    }
                }
            }
        }
    }
}