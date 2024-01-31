package com.example.jetpackcomposecatalog

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

/** Sliders
 * Permiten seleccionar un rango, y que el usuario lo deslize.
 * */

@Composable
fun MySlider() {
    var num by remember { mutableStateOf(0f) }
    Column {
        Slider(value = num, onValueChange = { num = it })
        Text(text = num.toString() )
    }
}
@Preview( showBackground = true )
@Composable
fun AdvanceSlider() {
    // Slider
    var num by remember { mutableStateOf(0f) }
    var completeValue by remember { mutableStateOf("") }
    // Range Slider
    var range by remember { mutableStateOf(0f..10f) }
    Column {
        Slider(
            value = num,
            onValueChange = { num = it }, // Sellama cuando se va moviendo
            onValueChangeFinished = { completeValue = num.toString() }, // Se llama cuando el componente se para
            valueRange = 0f..10f,
            steps = 9 )
        Text(text = completeValue )
        // Rangle Slider
        RangeSlider(
            valueRange = 0f..20f,
            value = range,
            onValueChange = { range = it },
            steps = 19)
        Text(text = "Valor inferior ${ range.start.toString() }")
        Text(text = "Valor Superior ${ range.endInclusive.toString() }")
    }
}