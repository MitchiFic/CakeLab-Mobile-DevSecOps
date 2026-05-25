package com.innovacode.cakelab.utils

object RegisterValidator {

    fun isValidName(name: String): Boolean {
        return name.trim().length >= 2
    }

    fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
        return email.trim().matches(emailRegex)
    }

    fun isValidPassword(password: String): Boolean {
        return password.length >= 6
    }

    fun isValidRegisterForm(
        name: String,
        email: String,
        password: String
    ): Boolean {
        return isValidName(name) &&
                isValidEmail(email) &&
                isValidPassword(password)
    }
}

