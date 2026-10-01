package com.example.androiddbconnectivity

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun DashboardScreen(dbHelper: DBHelper, email: String, onLogout: () -> Unit) {
    val user = remember { dbHelper.getUserDetails(email) }

    var showStudentDirectory by remember { mutableStateOf(false) }
    var showRegisteredAccounts by remember { mutableStateOf(false) }

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

        Button(
            onClick = { showStudentDirectory = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Student Directory")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { showRegisteredAccounts = true },
            modifier = Modifier.fillMaxWidth()
        ) {
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

    // Student Directory Dialog
    if (showStudentDirectory) {
        val students = remember { dbHelper.getAllUsers() }
        StudentListDialog(
            title = "Student Directory",
            users = students,
            onDismiss = { showStudentDirectory = false }
        )
    }

    // Registered Accounts Dialog
    if (showRegisteredAccounts) {
        val accounts = remember { dbHelper.getAllUsers() }
        StudentListDialog(
            title = "Registered Accounts",
            users = accounts,
            onDismiss = { showRegisteredAccounts = false }
        )
    }
}

@Composable
fun StudentListDialog(title: String, users: List<User>, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.85f),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(12.dp))

                if (users.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = null,
                                modifier = Modifier.size(48.dp),
                                tint = MaterialTheme.colorScheme.outline
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "No students registered yet",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.outline
                            )
                        }
                    }
                } else {
                    Text(
                        text = "${users.size} student(s) found",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.outline
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(users) { student ->
                            StudentCard(student = student)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))
                TextButton(
                    onClick = onDismiss,
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Close")
                }
            }
        }
    }
}

@Composable
fun StudentCard(student: User) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = student.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Reg No: ${student.regNo}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Dept: ${student.department}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Email: ${student.email}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Phone: ${student.phone}", style = MaterialTheme.typography.bodySmall)
        }
    }
}