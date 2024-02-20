package com.example.bolshakovmobile


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bolshakovmobile.API.RepositoryImpl
import com.example.bolshakovmobile.API.RetrofitInstance
import com.example.bolshakovmobile.screens.CodeScreen
import com.example.bolshakovmobile.screens.InformationScreen1
import com.example.bolshakovmobile.screens.SmartLab
import com.example.bolshakovmobile.screens.regScreen
import com.example.bolshakovmobile.ui.theme.BolshakovMobileTheme
import com.example.bolshakovmobile.viewModel.ViewModelMain

class MainActivity : ComponentActivity() {
        private val viewModelSmart by viewModels<ViewModelMain>(factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return ViewModelMain(RepositoryImpl(RetrofitInstance.apiSmartLab))
                            as T
                }
            }
        })
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BolshakovMobileTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ){
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "greetingScreen"
                    )
                    {
                        composable("greetingScreen") {
                            SmartLab(navController)
                        }
                        composable("firstDescriptionScreen") {
                            InformationScreen1(navController)
                        }
                        composable("LogScreen") {
                            regScreen(navController, viewModelSmart)
                        }
                        composable("EnterCodeScreen") {
                            CodeScreen(navController)
                        }
                        composable("LogScreen") {
                            regScreen(navController, viewModelSmart)
                        }
                    }


                }
            }
        }
    }
}

