package com.example.jetpackcomposecatalog.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.jetpackcomposecatalog.model.Rutas

@Preview(
    showBackground = true
)
@Composable
fun MySlider() {
    var value by remember {
        mutableStateOf(0f)
    }
    Slider(value = value, onValueChange = { value = it })
}
@Composable
fun Screen1(navigationController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan)
    ) {
        Text(text = "Pantalla 1", modifier = Modifier
            .align(Alignment.Center)
            .clickable {
                navigationController.navigate(Rutas.Pantalla2.route)
            })
    }
}
@Composable
fun Screen2(navigationController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green)
    ) {
        Text(text = "Pantalla 2", modifier = Modifier
            .align(Alignment.Center)
            .clickable {
                navigationController.navigate(Rutas.Pantalla3.route)
            })
    }
}
@Composable
fun Screen3(navigationController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Magenta)
    ) {
        Text(text = "Pantalla 3", modifier = Modifier
            .align(Alignment.Center)
            .clickable {
                navigationController.navigate(Rutas.Pantalla4.createRoot(29))
            })
    }
}
@Composable
fun Screen4(navigationController: NavHostController, age: Int) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow)
    ) {
        Text(text = "Tengo ${age.toString()} años", modifier = Modifier
            .align(Alignment.Center)
            .clickable {
                navigationController.navigate(Rutas.Pantalla5.createRoot("Teutle"))
            })
    }
}
@Composable
fun Screen5(navigationController: NavHostController, name: String?) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
    ) {
        Text(text = "Me llamo $name", modifier = Modifier.align( Alignment.Center ) )
    }
}