package com.example.bolshakovmobile.viewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bolshakovmobile.API.Repository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


class ViewModelMain (private val repository: Repository): ViewModel() {

    private val _showErrorToastChannel = Channel<Boolean>()
    val showErrorToastChannel = _showErrorToastChannel.receiveAsFlow()

    fun sendCodeEmail(email: String) {
        viewModelScope.launch {
            repository.sendCodeEmail(email).collect() {
                when (it) {
                    is com.example.bolshakovmobile.API.Result.Error -> {
                        _showErrorToastChannel.send(true)
                    }

                    is com.example.bolshakovmobile.API.Result.Success -> {
                        _showErrorToastChannel.send(false)
                    }
                }
            }
        }
    }
}