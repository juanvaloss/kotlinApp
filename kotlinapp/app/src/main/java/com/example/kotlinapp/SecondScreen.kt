package com.example.kotlinapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.kotlinapp.ui.theme.KotlinappTheme

class SecondScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinappTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            val intent = Intent(this@SecondScreen, ConvertActivity::class.java)
                            startActivity(intent)
                        },
                        modifier = Modifier.fillMaxWidth().padding(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Red, // Cambia el color de fondo del botón
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "Act1")
                    }
                    Button(
                        onClick = {
                            val intent = Intent(this@SecondScreen, RockPaperScissorsActivity::class.java)
                            startActivity(intent)
                        },
                        modifier = Modifier.fillMaxWidth().padding(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Red, // Cambia el color de fondo del botón
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "Act2")
                    }
                }
            }
        }
    }
}
