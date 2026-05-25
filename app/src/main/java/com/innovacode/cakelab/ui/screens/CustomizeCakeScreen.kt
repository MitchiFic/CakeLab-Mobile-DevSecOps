package com.innovacode.cakelab.ui.screens

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
import com.innovacode.cakelab.viewmodel.CakeLabViewModel

@Composable
fun CustomizeCakeScreen(
    viewModel: CakeLabViewModel,
    onAddCartClick: () -> Unit,
    onBackCatalogClick: () -> Unit
) {
    var sabor by remember { mutableStateOf("") }
    var relleno by remember { mutableStateOf("") }
    var tamano by remember { mutableStateOf("") }
    var decoracion by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }
    var indicaciones by remember { mutableStateOf("") }
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
            text = "Personaliza tu pastel",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = RosaViejo
        )

        Text(
            text = "Elige los detalles para crear un pastel único.",
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
                    value = sabor,
                    onValueChange = { sabor = it },
                    label = { Text("Sabor del pan") },
                    placeholder = { Text("Ej. Chocolate, vainilla, fresa") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = relleno,
                    onValueChange = { relleno = it },
                    label = { Text("Relleno") },
                    placeholder = { Text("Ej. Cajeta, fresa, crema") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = tamano,
                    onValueChange = { tamano = it },
                    label = { Text("Tamaño") },
                    placeholder = { Text("Ej. Chico, mediano, grande") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = decoracion,
                    onValueChange = { decoracion = it },
                    label = { Text("Color de decoración") },
                    placeholder = { Text("Ej. Rosa pastel, blanco, dorado") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = mensaje,
                    onValueChange = { mensaje = it },
                    label = { Text("Mensaje en el pastel") },
                    placeholder = { Text("Ej. Feliz cumpleaños") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = indicaciones,
                    onValueChange = { indicaciones = it },
                    label = { Text("Indicaciones especiales") },
                    placeholder = { Text("Ej. Sin nuez, con flores, temática") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 3
                )

                if (mensajeError.isNotEmpty()) {
                    Text(
                        text = mensajeError,
                        color = RosaViejo,
                        fontSize = 14.sp
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        if (
                            sabor.isBlank() ||
                            relleno.isBlank() ||
                            tamano.isBlank() ||
                            decoracion.isBlank()
                        ) {
                            mensajeError = "Completa sabor, relleno, tamaño y decoración"
                        } else {
                            mensajeError = ""

                            viewModel.guardarPersonalizacion(
                                nuevoSabor = sabor,
                                nuevoRelleno = relleno,
                                nuevoTamano = tamano,
                                nuevaDecoracion = decoracion,
                                nuevoMensaje = mensaje,
                                nuevasIndicaciones = indicaciones
                            )

                            onAddCartClick()
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = RosaViejo)
                ) {
                    Text("Agregar al carrito")
                }

                TextButton(
                    onClick = onBackCatalogClick,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("Volver al catálogo", color = RosaViejo)
                }
            }
        }
    }
}
