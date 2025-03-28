package com.example.mycalender.model

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomToolbar(onLogoutClick: () -> Unit) {
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

    TopAppBar(
        title = {
            Text(
                text = "Hello, Ram",
                color = Color.White,
                textAlign = TextAlign.Start
            )
        },
        actions = {
            Text(
                text = "Logout",
                color = Color.White,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .clickable {
                        showDialog = true
                    }
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF121212),
            titleContentColor = Color.White,
            actionIconContentColor = Color.White
        )
    )

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Confirm Logout") },
            text = { Text("Are you sure you want to logout?") },
            confirmButton = {
                TextButton(onClick = {
                    Toast.makeText(context, "You are logged out", Toast.LENGTH_SHORT).show()
                    showDialog = false
                    onLogoutClick()
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
