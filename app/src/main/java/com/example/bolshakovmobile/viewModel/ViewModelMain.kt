package com.example.bolshakovmobile.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bolshakovmobile.API.Repository
import com.example.bolshakovmobile.ValidMailLab.RegistrationFormEvent
import com.example.bolshakovmobile.ValidMailLab.ValidateEmail
import com.example.bolshakovmobile.ValidMailLab.ValidateTerms
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ViewModelMain (private val repository: Repository, private val validateEmail: ValidateEmail = ValidateEmail(),
                     private val validateTerms: ValidateTerms = ValidateTerms() ): ViewModel(){

    private val _showErrorToastChannel = Channel<Boolean>()
    val showErrorToastChannel = _showErrorToastChannel.receiveAsFlow()

  fun sendCodeEmail(email:String){
      viewModelScope.launch {
          repository.sendCodeEmail(email).collect(){
              when(it){
                  is com.example.bolshakovmobile.API.Result.Error ->{
                      _showErrorToastChannel.send(true)
                  }
                  is com.example.bolshakovmobile.API.Result.Success ->{
                      _showErrorToastChannel.send(false)
                  }
              }
          }
      }
  }

    var state by mutableStateOf(RegistrationFormState())
    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvent = validationEventChannel.receiveAsFlow()
    fun onEvent(event: RegistrationFormEvent){
        when(event) {
            is RegistrationFormEvent.EmailChanged ->{
                state = state.copy(email = event.email)

            }
            is RegistrationFormEvent.AcceptTherms ->{
                state = state.copy(acceptedTherms = event.isAccepted)
            }
            is RegistrationFormEvent.Submit -> {
                submmitData()
            }
        }
    }

    private fun submmitData() {
        val emailResult = validateEmail.execute(state.email)
        val thermsResult = validateTerms.execute(state.acceptedTherms)
        val hasError = listOf(
            emailResult,
            thermsResult
        ).any{ it.successful }

        if (hasError){
            state = state.copy(
                emailError = emailResult.errorMessage,
                thermsError = thermsResult.errorMessage
            )
            return
        }
        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }
    sealed class ValidationEvent{
        object Success: ValidationEvent()
    }


}