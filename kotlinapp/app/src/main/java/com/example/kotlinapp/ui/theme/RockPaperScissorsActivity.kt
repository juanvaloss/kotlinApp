package com.example.kotlinapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kotlinapp.ui.theme.KotlinappTheme
import kotlin.random.Random

class RockPaperScissorsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinappTheme {
                RockPaperScissorsGame {
                    // Intent para regresar al menu cuando se presiona el botón
                    val intent = Intent(this, SecondScreen::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}

@Composable
fun RockPaperScissorsGame(onBackToMenu: () -> Unit) {
    val options = listOf("Rock", "Paper", "Scissors")

    var userWins by remember { mutableStateOf(0) }
    var botWins by remember { mutableStateOf(0) }

    var gameResult by remember { mutableStateOf("") }
    var userChoice by remember { mutableStateOf<String?>(null) }
    var botChoice by remember { mutableStateOf<String?>(null) }

    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = Color.Red,
        contentColor = Color.White
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier.weight(1f),  // Empuja los elementos anteriores hacia arriba
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Choose your move:")
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { userChoice = "Rock" },
                    colors = buttonColors
                ) {
                    Text("Rock")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = { userChoice = "Paper" },
                    colors = buttonColors
                ) {
                    Text("Paper")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = { userChoice = "Scissors" },
                    colors = buttonColors
                ) {
                    Text("Scissors")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (userChoice != null) {
                        botChoice = options[Random.nextInt(3)]  // Elección aleatoria del bot
                        gameResult = determineWinner(userChoice!!, botChoice!!)

                        if (gameResult == "User wins!") {
                            userWins += 1
                        } else if (gameResult == "Bot wins!") {
                            botWins += 1
                        }
                    }
                },
                colors = buttonColors // Aplica los mismos colores
            ) {
                Text(text = "Play")
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (userChoice != null) {
                Text(text = "You chose: $userChoice")
            }
            if (botChoice != null) {
                Text(text = "Bot chose: $botChoice")
            }
            if (gameResult.isNotEmpty()) {
                Text(text = gameResult)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "User wins: $userWins")
            Text(text = "Bot wins: $botWins")
        }

        // Botón Back to Menu al final de la pantalla
        Button(
            onClick = { onBackToMenu() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            colors = buttonColors
        ) {
            Text("Back to Menu")
        }
    }
}

// Función para determinar el ganador
fun determineWinner(userChoice: String, botChoice: String): String {
    return when {
        userChoice == botChoice -> "It's a tie!"
        userChoice == "Rock" && botChoice == "Scissors" -> "User wins!"
        userChoice == "Paper" && botChoice == "Rock" -> "User wins!"
        userChoice == "Scissors" && botChoice == "Paper" -> "User wins!"
        else -> "Bot wins!"
    }
}

@Preview(showBackground = true)
@Composable
fun RockPaperScissorsPreview() {
    KotlinappTheme {
        RockPaperScissorsGame(onBackToMenu = {})
    }
}
