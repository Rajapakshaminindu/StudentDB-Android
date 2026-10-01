package com.example.androiddbconnectivity

import org.junit.Assert.*
import org.junit.Test

/**
 * Unit tests for SecurityUtils password hashing.
 */
class SecurityUtilsTest {

    @Test
    fun `hashPassword returns 64-character hex string for SHA-256`() {
        val hash = SecurityUtils.hashPassword("secret123")
        assertEquals(64, hash.length)
        // SHA-256 hex output is all lowercase hexadecimal
        assertTrue(hash.all { it.isDigit() || it in 'a'..'f' })
    }

    @Test
    fun `hashPassword is deterministic for same input`() {
        val hash1 = SecurityUtils.hashPassword("password")
        val hash2 = SecurityUtils.hashPassword("password")
        assertEquals(hash1, hash2)
    }

    @Test
    fun `hashPassword produces different hashes for different inputs`() {
        val hash1 = SecurityUtils.hashPassword("password1")
        val hash2 = SecurityUtils.hashPassword("password2")
        assertNotEquals(hash1, hash2)
    }

    @Test
    fun `verifyPassword returns true when password matches stored hash`() {
        val rawPassword = "mySecret!9"
        val stored = SecurityUtils.hashPassword(rawPassword)
        assertTrue(SecurityUtils.verifyPassword(rawPassword, stored))
    }

    @Test
    fun `verifyPassword returns false when password does not match stored hash`() {
        val stored = SecurityUtils.hashPassword("correctPassword")
        assertFalse(SecurityUtils.verifyPassword("wrongPassword", stored))
    }

    @Test
    fun `hashPassword handles empty string without exception`() {
        val hash = SecurityUtils.hashPassword("")
        assertEquals(64, hash.length)
    }
}
