package com.innovacode.cakelab.ui.screens

import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.innovacode.cakelab.ui.theme.CafeSuave
import com.innovacode.cakelab.ui.theme.Crema
import com.innovacode.cakelab.ui.theme.RosaViejo

@Composable
fun ProfileScreen(
    onSaveProfileClick: () -> Unit,
    onBackHomeClick: () -> Unit
) {
    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf("") }
    var perfilGuardado by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Crema)
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Mi perfil",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = RosaViejo
        )

        Text(
            text = "Consulta y actualiza tus datos personales.",
            fontSize = 16.sp,
            color = CafeSuave
        )

        Spacer(modifier = Modifier.height(20.dp))

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
                    placeholder = { Text("Ej. Franciella Martínez") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = correo,
                    onValueChange = { correo = it },
                    label = { Text("Correo electrónico") },
                    placeholder = { Text("Ej. usuario@correo.com") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = telefono,
                    onValueChange = { telefono = it.filter { char -> char.isDigit() } },
                    label = { Text("Teléfono") },
                    placeholder = { Text("Ej. 6671234567") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = direccion,
                    onValueChange = { direccion = it },
                    label = { Text("Dirección de envío") },
                    placeholder = { Text("Calle, colonia, ciudad") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 2
                )

                if (mensajeError.isNotEmpty()) {
                    Text(
                        text = mensajeError,
                        color = RosaViejo,
                        fontSize = 14.sp
                    )
                }

                if (perfilGuardado) {
                    Text(
                        text = "Perfil guardado correctamente",
                        color = CafeSuave,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        val nombreLimpio = nombre.trim()
                        val correoLimpio = correo.trim()

                        mensajeError = when {
                            nombreLimpio.isBlank() ||
                                    correoLimpio.isBlank() ||
                                    telefono.isBlank() ||
                                    direccion.isBlank() -> {
                                perfilGuardado = false
                                "Completa todos los campos"
                            }

                            nombreLimpio.length < 3 -> {
                                perfilGuardado = false
                                "El nombre debe tener al menos 3 caracteres"
                            }

                            !Patterns.EMAIL_ADDRESS.matcher(correoLimpio).matches() -> {
                                perfilGuardado = false
                                "Ingresa un correo válido"
                            }

                            telefono.length < 10 -> {
                                perfilGuardado = false
                                "El teléfono debe tener al menos 10 dígitos"
                            }

                            direccion.length < 8 -> {
                                perfilGuardado = false
                                "Ingresa una dirección más completa"
                            }

                            else -> {
                                perfilGuardado = true
                                ""
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = RosaViejo)
                ) {
                    Text("Guardar cambios")
                }

                TextButton(
                    onClick = onBackHomeClick,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("Volver al inicio", color = RosaViejo)
                }
            }
        }
    }
}
