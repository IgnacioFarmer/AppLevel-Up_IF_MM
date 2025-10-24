package com.example.applevel_up

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.applevel_up.model.Product
import com.example.applevel_up.ui.add_product.AddProductScreen
import com.example.applevel_up.ui.theme.AppLevelUpTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppLevelUpTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    var productList by remember { mutableStateOf(listOf<Product>()) }

    NavHost(navController = navController, startDestination = "productList") {
        composable("productList") {
            ProductScreen(products = productList, onAddProduct = { navController.navigate("agregarProducto") })
        }
        composable("agregarProducto") {
            AddProductScreen(
                onAddProduct = {
                    productList = productList + it
                    navController.popBackStack()
                },
                onCancel = { navController.popBackStack() }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(products: List<Product>, onAddProduct: () -> Unit, modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Lista de los productos") },
                navigationIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.level_up_gamer_logo),
                        contentDescription = "Logo de la tienda",
                        modifier = Modifier.padding(start = 12.dp)
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddProduct) {
                Text("+")
            }
        }
    ) { innerPadding ->
        ProductList(
            products = products,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun ProductList(products: List<Product>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(products) { product ->
            ProductItem(product = product)
        }
    }
}

@Composable
fun ProductItem(product: Product, modifier: Modifier = Modifier) {
    Column(modifier = modifier
        .padding(16.dp)
        .fillMaxWidth()) {
        Text(text = "Nombre: ${product.nombre}")
        Text(text = "Precio: $${product.precio}")
        Text(text = "Descripción: ${product.descripcion}")
    }
}

@Preview(showBackground = true)
@Composable
fun ProductScreenPreview() {
    AppLevelUpTheme {
        ProductScreen(products = emptyList(), onAddProduct = {})
    }
}
