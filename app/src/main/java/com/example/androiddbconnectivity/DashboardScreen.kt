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
    var userCount by remember { mutableStateOf(dbHelper.getUserCount()) }

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
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Total Students Quick Stat Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Total Enrolled",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        text = "$userCount Students",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    modifier = Modifier.size(36.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                userCount = dbHelper.getUserCount()
                showStudentDirectory = true
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Student Directory")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                userCount = dbHelper.getUserCount()
                showRegisteredAccounts = true
            },
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
        StudentListDialog(
            title = "Student Directory",
            dbHelper = dbHelper,
            onDismiss = { showStudentDirectory = false }
        )
    }

    // Registered Accounts Dialog
    if (showRegisteredAccounts) {
        StudentListDialog(
            title = "Registered Accounts",
            dbHelper = dbHelper,
            onDismiss = { showRegisteredAccounts = false }
        )
    }
}

@Composable
fun StudentListDialog(
    title: String,
    dbHelper: DBHelper,
    onDismiss: () -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    val allStudents = remember { dbHelper.getAllUsers() }
    val filteredStudents = remember(searchQuery) {
        if (searchQuery.isBlank()) {
            allStudents
        } else {
            dbHelper.searchUsers(searchQuery)
        }
    }

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
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    label = { Text("Search by name, reg no, or dept") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                if (filteredStudents.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
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
                                text = if (searchQuery.isBlank()) "No students registered yet" else "No matching students found",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.outline
                            )
                        }
                    }
                } else {
                    Text(
                        text = "${filteredStudents.size} student(s) found",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.outline
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    LazyColumn(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(filteredStudents) { student ->
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