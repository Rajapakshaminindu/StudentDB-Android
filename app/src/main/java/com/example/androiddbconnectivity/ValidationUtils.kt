package com.example.androiddbconnectivity

/**
 * Utility functions for user input validation in the StudentDB app.
 */
object ValidationUtils {

    private val EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()
    private val PHONE_REGEX = "^[+]?[0-9]{9,15}$".toRegex()

    /**
     * Checks if the given email string is valid.
     */
    fun isValidEmail(email: String): Boolean {
        return email.isNotBlank() && EMAIL_REGEX.matches(email.trim())
    }

    /**
     * Checks if the given phone number contains a valid format.
     */
    fun isValidPhone(phone: String): Boolean {
        return phone.isNotBlank() && PHONE_REGEX.matches(phone.trim().replace(" ", ""))
    }

    /**
     * Checks if the password meets minimum security criteria (at least 6 characters).
     */
    fun isValidPassword(password: String): Boolean {
        return password.length >= 6
    }

    /**
     * Verifies that all mandatory registration fields are non-blank.
     */
    fun areRegistrationFieldsValid(
        name: String,
        regNo: String,
        department: String,
        email: String,
        password: String,
        phone: String
    ): Boolean {
        return name.isNotBlank() &&
                regNo.isNotBlank() &&
                department.isNotBlank() &&
                isValidEmail(email) &&
                isValidPassword(password) &&
                isValidPhone(phone)
    }
}
