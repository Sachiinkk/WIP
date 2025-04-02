package com.example.mycalender.presentation.Component

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.mycalender.utils.ReusableAlertDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomToolbar(
    userName: String = "User",
    onLogoutClick: () -> Unit // ✅ FIXED: Added correct parameter type
) {
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            Text(text = "Hello, $userName", color = Color.White)
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
            containerColor = Color(0xFF1C1C1C),
            titleContentColor = Color.White,
            actionIconContentColor = Color.White
        )
    )

    if (showDialog) {
        ReusableAlertDialog(
            title = "Confirm Logout",
            message = "Are you sure you want to logout?",
            onDismiss = { showDialog = false },
            onConfirm = {
                Toast.makeText(context, "You are logged out", Toast.LENGTH_SHORT).show()
                onLogoutClick() // ✅ Trigger navigation from parent
                showDialog = false
            }
        )
    }
}
