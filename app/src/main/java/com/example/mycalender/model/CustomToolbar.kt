package com.example.mycalender.model

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomToolbar(onLogoutClick: () -> Unit) {
    val context = LocalContext.current

    TopAppBar(
        title = {
            Text(
                text = "Hello, Ram",
                color = Color.White
            )
        },
        actions = {
            Text(
                text = "Logout",
                color = Color.White,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .clickable {
                        onLogoutClick()
                    }
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF121212),
            titleContentColor = Color.White,
            actionIconContentColor = Color.White
        )
    )
}
