package com.innovacode.cakelab.utils

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class RegisterValidatorTest {

    @Test
    fun nombreVacio_retornarFalso() {
        val resultado = RegisterValidator.isValidName("")
        assertFalse(resultado)
    }

    @Test
    fun nombreConUnaLetra_retornarFalso() {
        val resultado = RegisterValidator.isValidName("A")
        assertFalse(resultado)
    }

    @Test
    fun nombreValido_retornarVerdadero() {
        val resultado = RegisterValidator.isValidName("Ana")
        assertTrue(resultado)
    }

    @Test
    fun correoSinArroba_retornarFalso() {
        val resultado = RegisterValidator.isValidEmail("usuario.com")
        assertFalse(resultado)
    }

    @Test
    fun correoConUnaLetra_retornarFalso() {
        val resultado = RegisterValidator.isValidEmail("a")
        assertFalse(resultado)
    }

    @Test
    fun correoValido_retornarVerdadero() {
        val resultado = RegisterValidator.isValidEmail("usuario@correo.com")
        assertTrue(resultado)
    }

    @Test
    fun passwordMenorASeisCaracteres_retornarFalso() {
        val resultado = RegisterValidator.isValidPassword("123")
        assertFalse(resultado)
    }

    @Test
    fun passwordValida_retornarVerdadero() {
        val resultado = RegisterValidator.isValidPassword("123456")
        assertTrue(resultado)
    }

    @Test
    fun formularioValido_retornarVerdadero() {
        val resultado = RegisterValidator.isValidRegisterForm(
            name = "Ana",
            email = "ana@correo.com",
            password = "123456"
        )

        assertTrue(resultado)
    }

    @Test
    fun formularioInvalido_retornarFalso() {
        val resultado = RegisterValidator.isValidRegisterForm(
            name = "A",
            email = "correoIncorrecto",
            password = "123"
        )

        assertFalse(resultado)
    }
}

