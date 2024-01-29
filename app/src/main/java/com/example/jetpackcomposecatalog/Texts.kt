package com.example.jetpackcomposecatalog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// State Hoisting.
// Poner las veriables mutables deben contenerlas el padre principal.

@Preview(showBackground = true)
@Composable
fun MyTextFieldAdvanced() {
    var myText by remember {
        mutableStateOf("")
    }
    var my2Text by remember {
        mutableStateOf("")
    }
    Column {
        TextField(value = myText, onValueChange = {
            myText = if( it.equals("a") ) {
                it.replace("a", "")
            } else {
                it
            }
        }, label = {
            Text(text = "Introduce tu nombre")
        })
        // TextField Outlined
        OutlinedTextField(
            value = my2Text,
            onValueChange = { my2Text = it },
            label = { Text(text = "Hola") },
            modifier = Modifier.padding(24.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Magenta,
                unfocusedContainerColor = Color.Blue
            )
        )
    }
}
@Composable
fun MyTextField( name:String, onValueChange: (String) -> Unit ) {
    TextField(value = name, onValueChange = { onValueChange( it )})
}


@Composable
fun MyText() {
    Column( Modifier.fillMaxSize() ) {
        // Los atributos de TextStyle los podemos usar sin tener que declarar la clase
        // Pero podemos uar la clase para trabajar con estilos.
        Text(text = "Texto de ejemplo")
        Text(text = "Texto de ejemplo", style = TextStyle(color = Color.Black))
        Text(text = "Texto de ejemplo sin txtstyle", color = Color.Magenta)
        Text(text = "Texto de ejemplo", fontWeight = FontWeight.ExtraBold)
        Text(
            text = "Texto de ejemplo", style = TextStyle(fontFamily = FontFamily.Cursive)
        )
        Text(text = "Texto de ejemplo", style = TextStyle(
            textDecoration = TextDecoration.Underline
        )
        )
        Text(text = "Texto de ejemplo", style = TextStyle(
            textDecoration = TextDecoration.combine(
                listOf( TextDecoration.Underline, TextDecoration.LineThrough )
            )
        )
        )

        Text(text = "Texto de Ejemplo", fontSize = 16.sp)
    }
}