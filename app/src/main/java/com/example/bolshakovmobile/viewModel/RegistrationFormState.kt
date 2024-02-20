package com.example.bolshakovmobile.viewModel

data class RegistrationFormState(
    val email: String = "",
    val emailError: String? = null,
    val acceptedTerms: Boolean = false,
    val termsError: String? = null
)
