package com.example.androiddbconnectivity

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun userModel_creationAndDefaults() {
        val user = User(
            name = "Test Student",
            regNo = "REG12345",
            department = "Computer Science",
            email = "student@example.com",
            phone = "+1234567890"
        )
        assertEquals(0, user.id)
        assertEquals("Test Student", user.name)
        assertEquals("REG12345", user.regNo)
        assertEquals("Computer Science", user.department)
        assertEquals("student@example.com", user.email)
        assertEquals("+1234567890", user.phone)
    }
}