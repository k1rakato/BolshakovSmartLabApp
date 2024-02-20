package com.example.bolshakovmobile.ValidMailLab

sealed class RegistrationFormEvent {
    data class EmailChanged(val email: String): RegistrationFormEvent()
    data class AcceptTherms(val isAccepted: Boolean): RegistrationFormEvent()

    object Submit: RegistrationFormEvent()
}