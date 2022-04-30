package com.template.ui.theme

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.template.viewModel.SecondViewModel
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun SecondScreen(
    navController: NavController,
    vm: SecondViewModel = viewModel(),
    coins: Int?
) {
    val state by vm.state.collectAsState()
    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = Unit) {
        vm.initState(coins)
    }
    if (state.isSpin) {
        LaunchedEffect(key1 = true) {
            var counter = 0
            while (counter < 5000) {
                scale.animateTo(
                    targetValue = 0.7f,
                    animationSpec = tween(
                        durationMillis = 24,
                        easing = {
                            OvershootInterpolator(4f).getInterpolation(it)
                        })
                )
                counter += 24
            }
            vm.updateIsBet(isBet = true)
            vm.updateIsWin()
            vm.updateOpenDialog(true)
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Your coins = ${state.coins}",
                    style = MaterialTheme.typography.h6
                )
                Spacer(modifier = Modifier.width(16.dp))
                TextField(
                    value = state.betText,
                    onValueChange = {
                        vm.updateBetCoins(it)
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = {
                        Text(text = if (state.betText != "wrong bet") "bet" else "Click bet")
                    },
                    enabled = state.isBet
                )
            }
            Divider(Modifier.fillMaxWidth(), color = MaterialTheme.colors.onPrimary)
            if (!state.isSpin) NoSpin(state = state.emoji[0])
            else {
                Row(
                    Modifier
                        .scale(scale.value)
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.Center
                )
                {
                    Divider(Modifier.width(0.5.dp), color = MaterialTheme.colors.onPrimary)
                    Column(
                        modifier = Modifier.border(
                            border = BorderStroke(
                                1.dp,
                                MaterialTheme.colors.onPrimary
                            )
                        )
                    ) {
                        Text(
                            text = state.emoji[Random.nextInt(0, 10)],
                            fontSize = 56.sp
                        )
                        Text(
                            text = state.emoji[Random.nextInt(0, 10)],
                            fontSize = 56.sp
                        )
                        Text(
                            text = state.emoji[Random.nextInt(0, 10)],
                            fontSize = 56.sp
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(
                        modifier = Modifier.border(
                            border = BorderStroke(
                                1.dp,
                                MaterialTheme.colors.onPrimary
                            )
                        )
                    ) {
                        Text(
                            text = state.emoji[Random.nextInt(0, 10)],
                            fontSize = 56.sp
                        )
                        Text(
                            text = state.emoji[Random.nextInt(0, 10)],
                            fontSize = 56.sp
                        )
                        Text(
                            text = state.emoji[Random.nextInt(0, 10)],
                            fontSize = 56.sp
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(
                        modifier = Modifier.border(
                            border = BorderStroke(
                                1.dp,
                                MaterialTheme.colors.onPrimary
                            )
                        )
                    ) {
                        Text(
                            text = state.emoji[Random.nextInt(0, 10)],
                            fontSize = 56.sp
                        )
                        Text(
                            text = state.emoji[Random.nextInt(0, 10)],
                            fontSize = 56.sp
                        )
                        Text(
                            text = state.emoji[Random.nextInt(0, 10)],
                            fontSize = 56.sp
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .align(Alignment.BottomCenter)
        ) {
            Button(
                onClick = {
                    vm.updateSpin(isSpin = false)
                    if (state.betText == "wrong bet") {
                        vm.updateBetCoins("")
                        vm.updateIsBet(isBet = true)
                    } else {
                        vm.updateBet(state.betText)
                        vm.updateIsBet(isBet = false)
                    }
                },
                modifier = Modifier
                    .height(44.dp)
                    .weight(1f)
                    .padding(start = 16.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
            ) {
                Text(
                    text = "bet",
                    style = MaterialTheme.typography.h4
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = {
                    if (state.betText != "" && state.betText != "wrong bet" && !state.isBet) {
                        vm.updateSpin(isSpin = true)
                    }
                },
                modifier = Modifier
                    .height(44.dp)
                    .weight(1f),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
            ) {
                Text(
                    text = "spin",
                    style = MaterialTheme.typography.h4
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = {
                    navController.navigate("FirstScreen/${state.coins}")
                },
                modifier = Modifier
                    .height(44.dp)
                    .weight(1f)
                    .padding(end = 16.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
            ) {
                Text(
                    text = "menu",
                    style = MaterialTheme.typography.h4
                )
            }
        }
    }
    if (state.openDialog) {
        WinDialog(
            state.isWin
        ) { coins ->
            vm.updateCoins(coins = coins)
            vm.updateOpenDialog(false)
            vm.updateSpin(false)
        }
    }
}

@Composable
fun NoSpin(
    state: String
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Center
    )
    {
        Divider(Modifier.width(0.5.dp), color = MaterialTheme.colors.onPrimary)
        Column(
            modifier = Modifier.border(
                border = BorderStroke(
                    1.dp,
                    MaterialTheme.colors.onPrimary
                )
            )
        ) {
            Text(
                text = state,
                fontSize = 56.sp
            )
            Text(
                text = state,
                fontSize = 56.sp
            )
            Text(
                text = state,
                fontSize = 56.sp
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.border(
                border = BorderStroke(
                    1.dp,
                    MaterialTheme.colors.onPrimary
                )
            )
        ) {
            Text(
                text = state,
                fontSize = 56.sp
            )
            Text(
                text = state,
                fontSize = 56.sp
            )
            Text(
                text = state,
                fontSize = 56.sp
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.border(
                border = BorderStroke(
                    1.dp,
                    MaterialTheme.colors.onPrimary
                )
            )
        ) {
            Text(
                text = state,
                fontSize = 56.sp
            )
            Text(
                text = state,
                fontSize = 56.sp
            )
            Text(
                text = state,
                fontSize = 56.sp
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
    }
}

@Composable
fun WinDialog(
    isWin: Boolean,
    onDismiss: (Int) -> Unit
) {

    val coins = Random.nextInt(50, 1000)

    AlertDialog(
        onDismissRequest = {
            onDismiss(coins)
        },
        title = {
            Text(
                style = MaterialTheme.typography.h4,
                text = if (isWin) "You win! Win = $coins" else "You lose!",
                textAlign = TextAlign.Center
            )
        },
        buttons = {
            Button(
                onClick = { onDismiss(coins) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
            ) {
                Text(
                    text = "Ok",
                    style = MaterialTheme.typography.h5
                )
            }
        }
    )
}
