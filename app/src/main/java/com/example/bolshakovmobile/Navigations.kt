package com.example.bolshakovmobile


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bolshakovmobile.screens.CodeScreen
import com.example.bolshakovmobile.screens.SmartLab
import com.example.bolshakovmobile.screens.regScreen

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = "greetingScreen")
    {
        composable("greetingScreen"){
            SmartLab(navController)
        }
        composable("LogScreen"){
            regScreen(navController)
        }
        composable("EnterCodeScreen"){
            CodeScreen(navController)
        }
        composable("LogScreen"){
            regScreen(navController)
        }
    }
}

