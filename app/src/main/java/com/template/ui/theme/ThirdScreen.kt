package com.template.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun ThirdScreen(
    navController: NavController,
    coins: Int?
) {
    var stateCoins by remember { mutableStateOf(0) }
    var isEffect by remember { mutableStateOf(false) }
    var isFirstButton by remember { mutableStateOf(false) }
    var isSecondButton by remember { mutableStateOf(false) }
    var isThirdButton by remember { mutableStateOf(false) }
    var isDialog by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = isEffect) {
        if (isFirstButton || isSecondButton || isThirdButton) {
            delay(2000)
            isDialog = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = 16.dp),
            text = "Lottery",
            style = MaterialTheme.typography.h3
        )
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        isFirstButton = true
                        isEffect = true
                        stateCoins = Random.nextInt(100, 1000)
                    },
                    modifier = Modifier
                        .height(56.dp),
                    enabled = !isEffect,
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
                ) {
                    Text(
                        text = "❓",
                        style = MaterialTheme.typography.h4
                    )
                }
                if (isFirstButton) {
                    Text(
                        text = "$stateCoins",
                        style = MaterialTheme.typography.h3
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        isSecondButton = true
                        isEffect = true
                        stateCoins = Random.nextInt(100, 1000)
                    },
                    modifier = Modifier
                        .height(56.dp),
                    enabled = !isEffect,
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
                ) {
                    Text(
                        text = "❓",
                        style = MaterialTheme.typography.h4
                    )
                }
                if (isSecondButton) {
                    Text(
                        text = "$stateCoins",
                        style = MaterialTheme.typography.h3
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        isThirdButton = true
                        isEffect = true
                        stateCoins = Random.nextInt(100, 1000)
                    },
                    modifier = Modifier
                        .height(56.dp),
                    enabled = !isEffect,
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
                ) {
                    Text(
                        text = "❓",
                        style = MaterialTheme.typography.h4
                    )
                }
                if (isThirdButton) {
                    Text(
                        text = "$stateCoins",
                        style = MaterialTheme.typography.h3
                    )
                }
            }
        }
    }
    if (isDialog) {
        LotteryDialog(
            onClick = {
                navController.navigate("FirstScreen/${coins?.plus(stateCoins) ?: stateCoins}")
            },
            addCoins = stateCoins
        )
    }
}

@Composable
fun LotteryDialog(
    onClick: () -> Unit,
    addCoins: Int
) {
    AlertDialog(
        onDismissRequest = {
            onClick()
        },
        title = {
            Text(
                style = MaterialTheme.typography.h5,
                text = "Your reward $addCoins coins",
                textAlign = TextAlign.Center
            )
        },
        buttons = {
            Row(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Button(
                    onClick = {
                        onClick()
                    },
                    modifier = Modifier
                        .weight(1f),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
                ) {
                    Text(
                        text = "claim",
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }
    )
}