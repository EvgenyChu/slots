package com.template.ui.theme

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.template.viewModel.FirstViewModel
import kotlin.random.Random

@Composable
fun FirstScreen(
    navController: NavController,
    vm: FirstViewModel = viewModel(),
    coins: Int?
) {
    val state by vm.state.collectAsState()
    if (coins != null) vm.updateCoins(coins)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
    ) {
        Column(
            Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                Icons.Filled.AccountBox,
                contentDescription = "Logo",
                tint = MaterialTheme.colors.onSecondary,
                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp)
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
            )
            Text(
                text = "slots",
                style = MaterialTheme.typography.h3
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
            )
            Text(
                text = "Your coins: ${state.coins}",
                style = MaterialTheme.typography.h4
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.BottomCenter)
        ) {
            Button(
                onClick = {
                    if (state.coins == 0) vm.updateDialog(true)
                    else navController.navigate("SecondScreen/${state.coins}")
                },
                modifier = Modifier
                    .height(56.dp)
                    .weight(1f),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
            ) {
                Text(
                    text = "Play",
                    style = MaterialTheme.typography.h4
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = {
                    Log.e("FirstScreen", "${state.coins}")
                    navController.navigate("ThirdScreen/${state.coins}")
                },
                modifier = Modifier
                    .height(56.dp)
                    .weight(1f),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
            ) {
                Text(
                    text = "Lottery",
                    style = MaterialTheme.typography.h4
                )
            }
        }
    }
    if (state.openCoinDialog) {
        CoinDialog(
            { addCoins ->
                vm.updateCoins(addCoins)
                navController.navigate("SecondScreen/$addCoins")
            },
            { vm.updateDialog(false) }
        )
    }
}

@Composable
fun CoinDialog(
    onClick: (Int) -> Unit,
    onDismiss: () -> Unit
) {
    val addCoins = Random.nextInt(100, 1000)
    AlertDialog(
        onDismissRequest = {
            onClick(addCoins)
            onDismiss()
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
                        onClick(addCoins)
                        onDismiss()
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