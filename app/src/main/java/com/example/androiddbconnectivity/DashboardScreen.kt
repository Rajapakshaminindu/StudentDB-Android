package com.example.androiddbconnectivity

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DashboardScreen(dbHelper: DBHelper, email: String, onLogout: () -> Unit) {
    val user = remember { dbHelper.getUserDetails(email) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Welcome, ${user?.name ?: "User"}!",
            fontSize = 24.sp,
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = "Student Directory Dashboard",
            fontSize = 18.sp,
            style = MaterialTheme.typography.bodyLarge
        )
        
        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = { /* TODO */ }, modifier = Modifier.fillMaxWidth()) {
            Text("Student Directory")
        }
        
        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { /* TODO */ }, modifier = Modifier.fillMaxWidth()) {
            Text("Registered Accounts")
        }
        
        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onLogout,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
        ) {
            Text("Logout")
        }
    }
}