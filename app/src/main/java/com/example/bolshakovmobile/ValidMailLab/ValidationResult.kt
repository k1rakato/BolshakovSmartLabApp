package com.example.bolshakovmobile.ValidMailLab

import android.webkit.ConsoleMessage
import com.example.bolshakovmobile.API.Result

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null

)
