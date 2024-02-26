package com.example.jetpackcomposecatalog

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.random.Random.Default.nextInt

@Composable
fun Animaciones() {
    var firstColor by remember { mutableStateOf( false ) }
    var small by remember { mutableStateOf( true ) }
    var isVisible by remember {
        mutableStateOf( false )
    }

    // Animacion de Color
    val animatedColor by animateColorAsState(
        targetValue = if (firstColor) Color.Red else Color.Yellow,
        label = "Color",
        animationSpec = tween( durationMillis = 2000 ), // Tiempo de la animacion
        finishedListener = {} // Cuando termina la animacion
    )
    // Animacion de Tamaño
    val animatedSize by animateDpAsState(
        targetValue = if( small ) 120.dp else 200.dp,
        label = "Tamaño",
        animationSpec = tween( 1000 )
    )

    Column( modifier = Modifier.fillMaxSize() ) {
        Box( modifier = Modifier
            .size(animatedSize)
            .background(animatedColor)
            .clickable {
                firstColor = !firstColor
                small = !small
                isVisible = !isVisible
            })
        // Animacion de Visibilidad
        AnimatedVisibility(
            visible = isVisible,
            enter = slideInHorizontally(), // Animacion de Entrada
            exit = slideOutHorizontally() // Animacion de Salida
        ) {
            Text(text = "Tetuan Pai", modifier = Modifier.background(Color.Magenta))
        }
    }
}
@Composable
fun AnimationwithCrossfade() {
    var type by remember { mutableStateOf( ComponentType.IMG ) }

    Column {
        Button(onClick = { type = getComponentTypeRandom() }) {
            Text(text = "Change")
        }
        Crossfade(targetState = type, label = "Crossfade") {
            when( it ) {
                ComponentType.IMG -> Icon( imageVector = Icons.Rounded.AccountBox, contentDescription = "Xd")
                ComponentType.TEXT -> Text(text = "Texto Mostrado")
                ComponentType.CONTROL -> Button(onClick = { }) {
                    Text(text = "Hola")
                }
            }
        }

    }
}
fun getComponentTypeRandom() : ComponentType {
    val myNumber = nextInt( from = 1, until = 3 )
    return when( myNumber ) {
        1 -> ComponentType.IMG
        2 -> ComponentType.CONTROL
        3 -> ComponentType.TEXT
        else -> ComponentType.TEXT
    }
}
enum class ComponentType{
    IMG, TEXT, CONTROL
}