package com.example.mycalender.model

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LogoutScreen(onLoginClick: () -> Unit) {
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("You are logged out", fontSize = 24.sp, color = Color.White)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { showDialog = true }) {
            Text("Login")
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Confirm Login") },
            text = { Text("Do you want to login again?") },
            confirmButton = {
                TextButton(onClick = {
                    Toast.makeText(context, "You are logged in", Toast.LENGTH_SHORT).show()
                    showDialog = false
                    onLoginClick()
                }) {
                    Text("Yes")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("No")
                }
            }
        )
    }
}
