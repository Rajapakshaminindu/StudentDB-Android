package com.example.androiddbconnectivity

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ValidationUtilsTest {

    @Test
    fun isValidEmail_validEmails_returnsTrue() {
        assertTrue(ValidationUtils.isValidEmail("student@university.edu"))
        assertTrue(ValidationUtils.isValidEmail("john.doe@example.com"))
        assertTrue(ValidationUtils.isValidEmail("user_123@domain.co.uk"))
    }

    @Test
    fun isValidEmail_invalidEmails_returnsFalse() {
        assertFalse(ValidationUtils.isValidEmail(""))
        assertFalse(ValidationUtils.isValidEmail("invalid-email"))
        assertFalse(ValidationUtils.isValidEmail("student@"))
        assertFalse(ValidationUtils.isValidEmail("@domain.com"))
    }

    @Test
    fun isValidPhone_validPhones_returnsTrue() {
        assertTrue(ValidationUtils.isValidPhone("+1234567890"))
        assertTrue(ValidationUtils.isValidPhone("0712345678"))
        assertTrue(ValidationUtils.isValidPhone("+94 77 123 4567"))
    }

    @Test
    fun isValidPhone_invalidPhones_returnsFalse() {
        assertFalse(ValidationUtils.isValidPhone(""))
        assertFalse(ValidationUtils.isValidPhone("123"))
        assertFalse(ValidationUtils.isValidPhone("abcdefghij"))
    }

    @Test
    fun isValidPassword_lengthCheck() {
        assertTrue(ValidationUtils.isValidPassword("secret123"))
        assertTrue(ValidationUtils.isValidPassword("123456"))
        assertFalse(ValidationUtils.isValidPassword("12345"))
        assertFalse(ValidationUtils.isValidPassword(""))
    }

    @Test
    fun areRegistrationFieldsValid_allValid_returnsTrue() {
        val result = ValidationUtils.areRegistrationFieldsValid(
            name = "John Doe",
            regNo = "CS/2026/001",
            department = "Computer Science",
            email = "john@example.com",
            password = "securePassword123",
            phone = "+1234567890"
        )
        assertTrue(result)
    }

    @Test
    fun areRegistrationFieldsValid_invalidField_returnsFalse() {
        val result = ValidationUtils.areRegistrationFieldsValid(
            name = "John Doe",
            regNo = "",
            department = "Computer Science",
            email = "john@example.com",
            password = "123",
            phone = "+1234567890"
        )
        assertFalse(result)
    }
}
