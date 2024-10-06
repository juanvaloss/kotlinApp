package com.example.kotlinapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kotlinapp.ui.theme.KotlinappTheme

class SecondActivity : ComponentActivity() {
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
                            // Acción para "Act1"
                        },
                        modifier = Modifier.fillMaxWidth().padding(8.dp)
                    ) {
                        Text(text = "Act1")
                    }
                    Button(
                        onClick = {
                            // Acción para "Act2"
                        },
                        modifier = Modifier.fillMaxWidth().padding(8.dp)
                    ) {
                        Text(text = "Act2")
                    }
                }
            }
        }
    }
}
