package com.example.androiddbconnectivity

import java.security.MessageDigest

/**
 * Utility object for password security operations.
 * Uses SHA-256 hashing as a baseline layer of protection.
 *
 * NOTE: For a production application, prefer PBKDF2WithHmacSHA256,
 * bcrypt, or Argon2 with a per-user salt stored alongside the hash.
 */
object SecurityUtils {

    /**
     * Hashes the given plain-text password using SHA-256.
     *
     * @param password The raw password string to hash.
     * @return Hexadecimal string representation of the SHA-256 hash.
     */
    fun hashPassword(password: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val hashBytes = digest.digest(password.toByteArray(Charsets.UTF_8))
        return hashBytes.joinToString("") { "%02x".format(it) }
    }

    /**
     * Verifies that the given plain-text password matches the stored hash.
     *
     * @param password   Raw password input from the user.
     * @param storedHash The previously hashed password stored in the database.
     * @return True if the hashed password matches the stored hash.
     */
    fun verifyPassword(password: String, storedHash: String): Boolean {
        return hashPassword(password) == storedHash
    }
}
