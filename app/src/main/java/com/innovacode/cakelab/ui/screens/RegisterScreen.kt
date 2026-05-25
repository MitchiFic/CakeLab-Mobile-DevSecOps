package com.innovacode.cakelab.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.innovacode.cakelab.ui.theme.CafeSuave
import com.innovacode.cakelab.ui.theme.Crema
import com.innovacode.cakelab.ui.theme.RosaViejo

@Composable
fun RegisterScreen(
    onRegisterClick: () -> Unit,
    onBackLoginClick: () -> Unit
) {
    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Crema)
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Crear cuenta",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = RosaViejo
        )

        Text(
            text = "Regístrate para personalizar tus pasteles",
            fontSize = 16.sp,
            color = CafeSuave
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(28.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier.padding(22.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre completo") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = correo,
                    onValueChange = { correo = it },
                    label = { Text("Correo electrónico") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = telefono,
                    onValueChange = { telefono = it.filter { char -> char.isDigit() } },
                    label = { Text("Teléfono") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = contrasena,
                    onValueChange = { contrasena = it },
                    label = { Text("Contraseña") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )

                if (mensajeError.isNotEmpty()) {
                    Text(
                        text = mensajeError,
                        color = RosaViejo,
                        fontSize = 14.sp
                    )
                }

                Button(
                    onClick = {
                        val nombreLimpio = nombre.trim()
                        val correoLimpio = correo.trim()
                        val telefonoLimpio = telefono.trim()
                        val contrasenaLimpia = contrasena.trim()

                        val correoValido = correoLimpio.contains("@") &&
                                correoLimpio.contains(".") &&
                                correoLimpio.length >= 8

                        if (
                            nombreLimpio.isEmpty() ||
                            correoLimpio.isEmpty() ||
                            telefonoLimpio.isEmpty() ||
                            contrasenaLimpia.isEmpty()
                        ) {
                            mensajeError = "Completa todos los campos"
                        } else if (nombreLimpio.length < 3) {
                            mensajeError = "El nombre debe tener al menos 3 caracteres"
                        } else if (!correoValido) {
                            mensajeError = "Ingresa un correo válido"
                        } else if (telefonoLimpio.length < 10) {
                            mensajeError = "El teléfono debe tener al menos 10 dígitos"
                        } else if (contrasenaLimpia.length < 6) {
                            mensajeError = "La contraseña debe tener mínimo 6 caracteres"
                        } else {
                            mensajeError = ""
                            onRegisterClick()
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = RosaViejo)
                ) {
                    Text("Registrarme")
                }

                TextButton(
                    onClick = onBackLoginClick,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("Ya tengo cuenta", color = RosaViejo)
                }
            }
        }
    }
}
