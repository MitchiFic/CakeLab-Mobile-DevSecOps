package com.innovacode.cakelab.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.RadioButton
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
import com.innovacode.cakelab.viewmodel.CakeLabViewModel

@Composable
fun PaymentScreen(
    viewModel: CakeLabViewModel,
    onConfirmPaymentClick: () -> Unit,
    onBackCartClick: () -> Unit
) {
    var metodoPago by remember { mutableStateOf("Tarjeta") }
    var titular by remember { mutableStateOf("") }
    var numeroTarjeta by remember { mutableStateOf("") }
    var vencimiento by remember { mutableStateOf("") }
    var cvv by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf("") }

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
            text = "Método de pago",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = RosaViejo
        )

        Text(
            text = "Completa la información para confirmar tu pedido.",
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
                Text(
                    text = "Selecciona una opción",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = RosaViejo
                )

                PaymentOption(
                    text = "Pago con tarjeta",
                    selected = metodoPago == "Tarjeta",
                    onClick = { metodoPago = "Tarjeta" }
                )

                PaymentOption(
                    text = "Pago contra entrega",
                    selected = metodoPago == "Contra entrega",
                    onClick = { metodoPago = "Contra entrega" }
                )

                PaymentOption(
                    text = "Pago al recoger en sucursal",
                    selected = metodoPago == "Sucursal",
                    onClick = { metodoPago = "Sucursal" }
                )

                if (metodoPago == "Tarjeta") {
                    OutlinedTextField(
                        value = titular,
                        onValueChange = { titular = it },
                        label = { Text("Nombre del titular") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = numeroTarjeta,
                        onValueChange = { numeroTarjeta = it.filter { char -> char.isDigit() } },
                        label = { Text("Número de tarjeta") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = vencimiento,
                        onValueChange = { vencimiento = it },
                        label = { Text("Vencimiento MM/AA") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = cvv,
                        onValueChange = { cvv = it.filter { char -> char.isDigit() } },
                        label = { Text("CVV") },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                if (metodoPago != "Sucursal") {
                    OutlinedTextField(
                        value = direccion,
                        onValueChange = { direccion = it },
                        label = { Text("Dirección de entrega") },
                        modifier = Modifier.fillMaxWidth(),
                        minLines = 2
                    )
                }

                if (mensajeError.isNotEmpty()) {
                    Text(
                        text = mensajeError,
                        color = RosaViejo,
                        fontSize = 14.sp
                    )
                }

                Text(
                    text = "Total a pagar: $${viewModel.total} MXN",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = CafeSuave
                )

                Button(
                    onClick = {
                        if (metodoPago == "Tarjeta") {
                            when {
                                titular.isBlank() -> mensajeError = "Ingresa el nombre del titular"
                                numeroTarjeta.length < 16 -> mensajeError = "El número de tarjeta debe tener 16 dígitos"
                                vencimiento.isBlank() -> mensajeError = "Ingresa el vencimiento de la tarjeta"
                                cvv.length < 3 -> mensajeError = "El CVV debe tener al menos 3 dígitos"
                                direccion.isBlank() -> mensajeError = "Ingresa la dirección de entrega"
                                else -> {
                                    mensajeError = ""
                                    onConfirmPaymentClick()
                                }
                            }
                        } else if (metodoPago == "Contra entrega") {
                            if (direccion.isBlank()) {
                                mensajeError = "Ingresa la dirección de entrega"
                            } else {
                                mensajeError = ""
                                onConfirmPaymentClick()
                            }
                        } else if (metodoPago == "Sucursal") {
                            mensajeError = ""
                            onConfirmPaymentClick()
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = RosaViejo)
                ) {
                    Text("Confirmar pedido")
                }

                TextButton(
                    onClick = onBackCartClick,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("Volver al carrito", color = RosaViejo)
                }
            }
        }
    }
}

@Composable
fun PaymentOption(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick
        )

        Text(
            text = text,
            color = CafeSuave,
            fontSize = 15.sp
        )
    }
}

