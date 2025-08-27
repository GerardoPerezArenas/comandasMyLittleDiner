package com.gerardo.comandas

import com.gerardo.comandas.data.repositories.impl.AuthRepositoryImpl
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
    fun authRepository_login_withValidCode_returnsTrue() {
        val authRepository = AuthRepositoryImpl()
        assertTrue(authRepository.login("171", ""))
        assertTrue(authRepository.login("222", ""))
        assertTrue(authRepository.login("333", ""))
        assertTrue(authRepository.login("444", ""))
    }
    
    @Test
    fun authRepository_login_withInvalidCode_returnsFalse() {
        val authRepository = AuthRepositoryImpl()
        assertFalse(authRepository.login("999", ""))
        assertFalse(authRepository.login("", ""))
        assertFalse(authRepository.login("invalid", ""))
    }
}