package com.example.kotlinapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kotlinapp.ui.theme.KotlinappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinappTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginScreen(
                        modifier = Modifier.padding(innerPadding),
                        onLoginSuccess = {
                            val intent = Intent(this, SecondScreen::class.java)
                            startActivity(intent)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun LoginScreen(modifier: Modifier = Modifier, onLoginSuccess: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loginSuccess by remember { mutableStateOf(false) }
    val hardcodedUsername = "user"
    val hardcodedPassword = "password"

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        if (loginSuccess) {
            onLoginSuccess()  // Llamar cuando el login es exitoso
        } else {
            BasicTextField(
                value = username,
                onValueChange = { username = it },
                modifier = Modifier.fillMaxWidth(),
                decorationBox = { innerTextField ->
                    Box(Modifier.padding(8.dp)) {
                        if (username.isEmpty()) Text(text = "Username")
                        innerTextField()
                    }
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            BasicTextField(
                value = password,
                onValueChange = { password = it },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                decorationBox = { innerTextField ->
                    Box(Modifier.padding(8.dp)) {
                        if (password.isEmpty()) Text(text = "Password")
                        innerTextField()
                    }
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    loginSuccess = (username == hardcodedUsername && password == hardcodedPassword)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red, // Cambia el color de fondo del botón
                    contentColor = Color.White
                )
            ) {
                Text(text = "Login")
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    KotlinappTheme {
        LoginScreen(onLoginSuccess = {})
    }
}
