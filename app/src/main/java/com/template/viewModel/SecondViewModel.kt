package com.template.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.random.Random

class SecondViewModel() : ViewModel() {
    private val _state: MutableStateFlow<SecondState> = MutableStateFlow(SecondState())

    val state: StateFlow<SecondState>
        get() = _state

    val currentState: SecondState
        get() = state.value

    suspend fun initState(coins: Int?) {
        _state.value = currentState.copy(coins = coins ?: 0)
    }

    fun updateCoins(coins: Int) {
        val coinsWin: Int = if (currentState.isWin) currentState.coins+coins
        else currentState.coins-currentState.bet
            _state.value = currentState.copy(coins = coinsWin)
    }

    fun updateIsWin(){
        val isWin = Random.nextInt(0, 2) == 1
        _state.value = currentState.copy(isWin = isWin)
    }

 fun updateSpin(isSpin: Boolean) {
        _state.value = currentState.copy(isSpin = isSpin)
    }

    fun updateIsBet(isBet: Boolean) {
        _state.value = currentState.copy(isBet = isBet)
    }

    fun updateBetCoins(bet: String) {
        _state.value = currentState.copy(betText = bet)
    }

    fun updateOpenDialog(openDialog: Boolean){
        _state.value = currentState.copy(openDialog = openDialog, betText = "")
    }

    fun updateBet(betCoin: String) {
        try {
            if (betCoin.toInt() > currentState.coins || betCoin.toInt() == 0) _state.value = currentState.copy(betText = "wrong bet")
            else {
                _state.value = currentState.copy(betText = betCoin, bet = betCoin.toInt())
            }
        }
        catch (e:Exception){
            _state.value = currentState.copy(betText = "wrong bet")
        }
    }
}

data class SecondState(
    val coins: Int = 0,
    val emoji: List<String> = listOf(
        "⭐️",
        "\uD83C\uDF52",
        "\uD83C\uDF51",
        "\uD83C\uDF4E",
        "♥️",
        "♣️",
        "\uD83C\uDF53",
        "\uD83D\uDC44",
        "\uD83D\uDCA9",
        "\uD83E\uDD2A"
    ),
    val betText: String = "",
    val bet: Int = 0,
    val isSpin: Boolean = false,
    val isBet: Boolean = true,
    val isWin: Boolean = true,
    val openDialog: Boolean = false
)