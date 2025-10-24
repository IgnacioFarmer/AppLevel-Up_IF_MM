package com.example.applevel_up.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.applevel_up.R
import com.example.applevel_up.ui.theme.AppLevelUpTheme

@Composable
fun MainScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo de la tienda
        Image(
            painter = painterResource(id = R.drawable.level_up_gamer_logo),
            contentDescription = "Logo de la tienda",
            modifier = Modifier
                .padding(bottom = 48.dp)
                .height(100.dp)
        )

        Text(
            "¡Bienvenido a Level Up!",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Botón para ver el catálogoo
        Button(
            onClick = { navController.navigate("productList") },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text("Ver Catálogo de Productos")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    AppLevelUpTheme {
        MainScreen(rememberNavController())
    }
}