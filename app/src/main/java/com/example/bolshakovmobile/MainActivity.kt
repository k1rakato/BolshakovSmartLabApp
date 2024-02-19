package com.example.bolshakovmobile


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.bolshakovmobile.ui.theme.BolshakovMobileTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BolshakovMobileTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ){
                   Navigation()
                }

            }

        }
    }
}


