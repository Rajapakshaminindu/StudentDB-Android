package com.example.androiddbconnectivity

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class UserTest {

    @Test
    fun user_creationWithDefaultId_initializesCorrectly() {
        val user = User(
            name = "Alice Smith",
            regNo = "IT/2026/101",
            department = "Software Engineering",
            email = "alice@example.com",
            phone = "+94771234567"
        )

        assertEquals(0, user.id)
        assertEquals("Alice Smith", user.name)
        assertEquals("IT/2026/101", user.regNo)
        assertEquals("Software Engineering", user.department)
        assertEquals("alice@example.com", user.email)
        assertEquals("+94771234567", user.phone)
    }

    @Test
    fun user_equalityAndHashCode_comparesValues() {
        val user1 = User(1, "Bob", "IT/002", "CS", "bob@example.com", "1234567890")
        val user2 = User(1, "Bob", "IT/002", "CS", "bob@example.com", "1234567890")
        val user3 = User(2, "Bob", "IT/002", "CS", "bob@example.com", "1234567890")

        assertEquals(user1, user2)
        assertEquals(user1.hashCode(), user2.hashCode())
        assertNotEquals(user1, user3)
    }

    @Test
    fun user_copy_updatesSpecifiedProperties() {
        val original = User(1, "Charlie", "CS/003", "IT", "charlie@example.com", "0711111111")
        val updated = original.copy(department = "Cyber Security", phone = "0722222222")

        assertEquals("Charlie", updated.name)
        assertEquals("Cyber Security", updated.department)
        assertEquals("0722222222", updated.phone)
        assertEquals(original.id, updated.id)
    }
}
