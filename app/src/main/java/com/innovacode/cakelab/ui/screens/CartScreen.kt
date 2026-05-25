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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
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
fun CartScreen(
    viewModel: CakeLabViewModel,
    onPaymentClick: () -> Unit,
    onBackCatalogClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Crema)
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Carrito de compras",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = RosaViejo
        )

        Text(
            text = "Revisa tu pastel personalizado antes de continuar.",
            fontSize = 16.sp,
            color = CafeSuave
        )

        Spacer(modifier = Modifier.height(22.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(28.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier.padding(22.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Pastel personalizado",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = RosaViejo
                )

                Text("Sabor: ${viewModel.sabor}", color = CafeSuave)
                Text("Relleno: ${viewModel.relleno}", color = CafeSuave)
                Text("Tamaño: ${viewModel.tamano}", color = CafeSuave)
                Text("Decoración: ${viewModel.decoracion}", color = CafeSuave)

                if (viewModel.mensaje.isNotBlank()) {
                    Text("Mensaje: ${viewModel.mensaje}", color = CafeSuave)
                }

                if (viewModel.indicaciones.isNotBlank()) {
                    Text("Indicaciones: ${viewModel.indicaciones}", color = CafeSuave)
                }
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Total: $${viewModel.total} MXN",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = CafeSuave
                )

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = onPaymentClick,
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = RosaViejo)
                ) {
                    Text("Continuar al pago")
                }

                TextButton(
                    onClick = onBackCatalogClick,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("Seguir comprando", color = RosaViejo)
                }
            }
        }
    }
}

