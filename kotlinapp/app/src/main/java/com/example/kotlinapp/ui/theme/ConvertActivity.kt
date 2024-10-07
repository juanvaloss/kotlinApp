package com.example.kotlinapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kotlinapp.ui.theme.KotlinappTheme

class ConvertActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinappTheme {
                CelsiusToFahrenheitConverter {
                    // Intent para regresar a la MainActivity cuando se presiona el botón
                    val intent = Intent(this, SecondScreen::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}

@Composable
fun CelsiusToFahrenheitConverter(onBackToMenu: () -> Unit) {
    var celsius by remember { mutableStateOf("") }
    var fahrenheit by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        BasicTextField(
            value = celsius,
            onValueChange = { celsius = it },
            modifier = Modifier.fillMaxWidth(),
            decorationBox = { innerTextField ->
                Box(Modifier.padding(8.dp)) {
                    if (celsius.isEmpty()) Text("Enter Celsius")
                    innerTextField()
                }
            }
        )
        Spacer(modifier = Modifier.height(30.dp))

        fahrenheit?.let {
            Text(text = "Fahrenheit: $it")
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                val celsiusValue = celsius.toDoubleOrNull()
                if (celsiusValue != null) {
                    val fahrenheitValue = celsiusValue * 9 / 5 + 32
                    fahrenheit = String.format("%.2f", fahrenheitValue)
                } else {
                    fahrenheit = "Invalid input"
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red,
                contentColor = Color.White
            )
        ) {
            Text("Convert to Fahrenheit")
        }

        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = { onBackToMenu() },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red,
                contentColor = Color.White
            )
        ) {
            Text("Back to Menu")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CelsiusToFahrenheitConverterPreview() {
    KotlinappTheme {
        CelsiusToFahrenheitConverter { }
    }
}
