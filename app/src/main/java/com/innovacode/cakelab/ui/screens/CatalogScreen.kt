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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.innovacode.cakelab.ui.theme.CafeSuave
import com.innovacode.cakelab.ui.theme.Crema
import com.innovacode.cakelab.ui.theme.RosaViejo

data class CakeItem(
    val name: String,
    val description: String,
    val price: String
)

@Composable
fun CatalogScreen(
    onCustomizeClick: () -> Unit
) {
    val cakes = listOf(
        CakeItem("Pastel de Chocolate", "Pan de chocolate con betún cremoso.", "$350"),
        CakeItem("Pastel de Fresa", "Pastel suave con relleno de fresa natural.", "$320"),
        CakeItem("Pastel Vainilla Rosé", "Pan de vainilla con decoración rosa pastel.", "$300"),
        CakeItem("Pastel Red Velvet", "Clásico red velvet con queso crema.", "$380")
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Crema)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(bottom = 20.dp)
    ) {
        item {
            Text(
                text = "Catálogo de pasteles",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = RosaViejo
            )

            Text(
                text = "Elige un pastel base y personalízalo a tu gusto.",
                fontSize = 16.sp,
                color = CafeSuave
            )

            Spacer(modifier = Modifier.height(10.dp))
        }

        items(cakes) { cake ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                Column(
                    modifier = Modifier.padding(18.dp)
                ) {
                    Text(
                        text = cake.name,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = RosaViejo
                    )

                    Text(
                        text = cake.description,
                        fontSize = 15.sp,
                        color = CafeSuave
                    )

                    Text(
                        text = cake.price,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = CafeSuave
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = onCustomizeClick,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = RosaViejo)
                    ) {
                        Text("Personalizar")
                    }
                }
            }
        }
    }
}

