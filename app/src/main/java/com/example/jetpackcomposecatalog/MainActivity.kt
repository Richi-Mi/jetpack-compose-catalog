package com.example.jetpackcomposecatalog

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecatalog.ui.theme.JetpackComposeCatalogTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeCatalogTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    // State Hoisting
                    /*var myText by remember {
                        mutableStateOf("")
                    }
                    MyTextField(name = myText, onValueChange = {
                        myText = it
                    })*/
                    // MyButtonExample()
                    MyProggressAdvance()
                }
            }
        }
    }
}
@Composable
fun MyProggressAdvance() {
    var progreso by remember {
        mutableStateOf( 0.1f )
    }
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator( progress = progreso )

        Row( modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center ){
            Button(onClick = { progreso += 0.1f }) {
                Text(text = "Incrementar")
            }
            Button(onClick = { progreso -= 0.1f }) {
                Text(text = "Reducir")
            }
        }
    }
}
@Composable
fun MyProggressBar() {
    var showLoading by remember {
        mutableStateOf( false )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if( showLoading ) {
            CircularProgressIndicator( color = Color.Red, strokeWidth = 3.dp )
            LinearProgressIndicator(
                modifier = Modifier.padding( top = 16.dp ),
                color = Color.Red
                // backgroundColor
            )
        }
        Button(onClick = { showLoading = !showLoading }) {
            Text(text = "Cargar Perfil")
        }
    }
}

@Composable
fun MyImage() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "ejemplo",
        alpha = 0.5f )
}
@Composable
fun MyImageAdvanced() {
    Column {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "ejemplo",
            modifier = Modifier
                .clip(CircleShape)
                .border(5.dp, Color.Red, CircleShape))
        //Modifier.clip( RoundedCornerShape(25f )) )

        // Iconos
        Icon(
            imageVector = Icons.Rounded.Star,
            contentDescription = "Iconos",
            tint = Color.Red
        )
    }

}

@Composable
fun MyButtonExample() {
    
    var enable by remember {
        mutableStateOf( true )
    } // Variable mutable de tipo boolean
    
    Column(
        Modifier
            .fillMaxSize() // Ocupa toda la pantalla
            .padding(24.dp) ) { // Espaciado interno de 24.dp
        Button(onClick = { enable = false },
            // Botones con estados.
            enabled = enable, // Uso de la variable
            
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Cyan,
                contentColor = Color.Red,
                disabledContainerColor = Color.Black, // Cuando el boton esta desabilitado.
                disabledContentColor = Color.DarkGray
            ),
            border = BorderStroke( 5.dp, Color.Green )
        ){
            Text(text = "Hola")
            // Aqui puedes meter cuantos componentes quieras, eso incluye Columnas FIlas, etc.
        }
        // Outlined. Boton sin background
        OutlinedButton(onClick = { }, Modifier.padding( top = 20.dp )) {
            Text(text = "Hola")
        }
        
        // Text Button, Un texto clickable
        TextButton(onClick = {  }) {
            Text(text = "Holaaa")
        }
    }
}

@Composable
fun StateExample() {
    Column( modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally ) {

        // remember. - Recuerda los elementos cuando el componente se destruye y se vuelve a crear.
        // rememberSavable. - Recuerda los elementos cuando la ACTIVIDAD se destruye y se vuelve a crear. ( Cuando rota la pantalla )

        /*
        val counter = remember {
            mutableIntStateOf( 0 )
        } Acceder a valores counter.value o modificar counter.value++
        */
        
        var counter by remember { mutableStateOf( 0 ) }

        Button(onClick = { counter++ }) {
            Text(text = "Aumentar")
        }

        Text(text = "Has pulsado el boton $counter veces")
    }
}

/** Layouts.
 * Box - Similar a FrameLayout */

@Composable
fun MyRow() {
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "Ejemplo 1")
        Text(text = "Ejemplo 2")
        Text(text = "Ejemplo 1")
        Text(text = "Ejemplo 2")
        Text(text = "Ejemplo 1")
        Text(text = "Ejemplo 2")
        Text(text = "Ejemplo 2")
        Text(text = "Ejemplo 1")
        Text(text = "Ejemplo 2")
    }
}

@Composable
fun MyColumn() {
    Column(
        modifier = Modifier.fillMaxSize()
        // verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Ejemplo1", modifier = Modifier
                .background(Color.Red)
                .weight(1f)
        )
        Text(
            text = "Ejemplo2", modifier = Modifier
                .background(Color.Black)
                .weight(2f)
        )
        Text(
            text = "Ejemplo3", modifier = Modifier
                .background(Color.Blue)
                .weight(2f)
        )
        Text(
            text = "Ejemplo4", modifier = Modifier
                .background(Color.Cyan)
                .weight(1f)
        )
    }
}

@Composable
fun MyBox() {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {

        Box(
            modifier = Modifier
                .background(Color.Red)
                .width(200.dp)
                //.verticalScroll(rememberScrollState() )
                .height(300.dp), contentAlignment = Alignment.BottomCenter
        ) {

            Text(text = "Madre mia willy que haces aqui compañero")
            Text(text = "XD")

        }
    }
}

@Composable
fun Ejercicio1() {
    Column( modifier = Modifier.fillMaxSize() ) {

        Box(modifier = Modifier
            .weight(1f)
            .background(Color.Cyan)
            .fillMaxWidth(), contentAlignment = Alignment.Center) {

            Text( text = "Ejemplo 1" )

        }
        Spacer(modifier = Modifier.height( 20.dp) )// Crea un espacio en la pantalla.
        Row( modifier = Modifier
            .weight(1f)
            .fillMaxWidth() ) {

            Box( modifier = Modifier
                .background(Color.Red)
                .fillMaxHeight()
                .weight(1f),
                contentAlignment = Alignment.Center ) {

                Text(text = "Ejemplo 2")

            }
            Spacer( modifier = Modifier.width(30.dp) ) // Crea un espacio en la pantalla.
            Box ( modifier = Modifier
                .background(Color.Green)
                .fillMaxHeight()
                .weight(1f),
            contentAlignment = Alignment.Center ){

                Text(text = "Ejemplo 3")

            }

        }
        Spacer(modifier = Modifier.height( 20.dp) )// Crea un espacio en la pantalla.
        Box(
            modifier = Modifier
                .weight(1f)
                .background(Color.Magenta)
                .fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter) {
            Text(text = "Ejemplo 4")
        }
    }
}

@Preview(name = "Button", showBackground = true)
@Composable
fun DefaultPreview() {
    MyImageAdvanced()
}