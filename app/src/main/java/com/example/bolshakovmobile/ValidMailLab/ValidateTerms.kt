package com.example.bolshakovmobile.ValidMailLab

import android.util.Patterns

class ValidateTerms {
    fun execute(acceptedTerms: Boolean): ValidationResult  {
        if (!acceptedTerms) {
            return ValidationResult(
                successful = false,
                errorMessage = "The email can't be blank"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}