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
fun OrdersScreen(
    viewModel: CakeLabViewModel,
    onBackHomeClick: () -> Unit,
    onNewOrderClick: () -> Unit
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
            text = "Mis pedidos",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = RosaViejo
        )

        Text(
            text = "Consulta el estado de tus pedidos realizados.",
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
                    text = "Pedido confirmado",
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

                Text("Total: $${viewModel.total} MXN", color = CafeSuave)

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = onNewOrderClick,
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = RosaViejo)
                ) {
                    Text("Realizar otro pedido")
                }

                Button(
                    onClick = onBackHomeClick,
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = RosaViejo)
                ) {
                    Text("Volver al inicio")
                }
            }
        }
    }
}

