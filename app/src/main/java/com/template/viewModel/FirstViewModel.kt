package com.template.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FirstViewModel() : ViewModel() {
    private val _state: MutableStateFlow<FirstState> = MutableStateFlow(FirstState())

    val state: StateFlow<FirstState>
        get() = _state

    val currentState: FirstState
        get() = state.value

    fun updateCoins(addCoins: Int){
        _state.value = currentState.copy(coins = addCoins)
    }

    fun updateDialog(openCoinDialog: Boolean){
        _state.value = currentState.copy(openCoinDialog = openCoinDialog)
    }
}

data class FirstState(
    val coins: Int = 1000,
    val openCoinDialog: Boolean = false
)