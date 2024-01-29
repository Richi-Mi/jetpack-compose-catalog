package com.example.jetpackcomposecatalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat.BadgeIconType
import com.example.jetpackcomposecatalog.ui.theme.JetpackComposeCatalogTheme

/**
 * @author Ricardo
 * @param title Nombre del checkbox.
 * @param selected Estado del boton
 * */
data class CheckInfo(
    val title: String,
    var selected: Boolean = false,
    var onCheckedChange: (Boolean) -> Unit
)

class SelectionActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeCatalogTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    /*
                    val myOptions = getOptions(titles = listOf("Richi", "Ejemplo", "Grogu") )
                    Column {
                        MyTriStatusCheckBox()
                        myOptions.forEach {
                            MyCheckBoxPersonalized( checkInfo = it )
                        }
                        var name by remember {
                            mutableStateOf("radio 1")
                        }
                        MyRadioButtonList( name ) { name = it }
                    }*/
                    Column {
                        MyCard()
                        // Surface {}
                        Divider(Modifier.fillMaxWidth(), color = Color.Red) // Linea de division
                        MyBadgeBox()
                        MyDropDownMenu()
                    }

                }
            }
        }
    }
}

@Composable
fun MyDropDownMenu() {
    var selectedText by remember {
        mutableStateOf("")
    }
    var expanded by remember {
        mutableStateOf(false)
    }
    val desserts = listOf("Helado", "Chocolate", "Fruta", "Nuez")
    Column(Modifier.padding(20.dp)) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            enabled = false,
            readOnly = true, // Solo Leer,
            modifier = Modifier
                .clickable { expanded = true }
                .fillMaxWidth()
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            desserts.forEach { dessert ->
                DropdownMenuItem(
                    text = { 
                        Text(text = dessert)
                           }, 
                    onClick = {
                    expanded = false
                    selectedText = dessert
                })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBadgeBox() {
    BadgedBox(
        modifier = Modifier.padding(20.dp),
        badge = {
            Badge(
                content = {
                    Text(modifier = Modifier.padding(2.dp), text = "10")
                },
                contentColor = Color.White,
                modifier = Modifier.padding(2.dp)
            )
        },
    ) {
        Icon(
            modifier = Modifier.size(50.dp),
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "l",
            tint = Color.Blue
        )
    }
}

/** Surface es lo mismo que Card, solo que en Card vienen ya predispuestos los elementos
 * Las Card son surface con contenido ya preparado.
 * */
@Composable
fun MyCard() {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Black,
            contentColor = Color.Green
        ),
        shape = MaterialTheme.shapes.small,
        border = BorderStroke(4.dp, Color.Green)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(text = "Ejemplo 1")
            Text(text = "Ejemplo 2")
        }
    }
}

@Composable
fun MyRadioButtonList(selected: String, onItemSelected: (String) -> Unit) {

    Column(Modifier.fillMaxWidth()) {
        Row(Modifier.fillMaxWidth()) {
            RadioButton(
                selected = selected == "radio 1",
                onClick = { onItemSelected("radio 1") })
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "radio 1")

            RadioButton(
                selected = selected == "radio 2",
                onClick = { onItemSelected("radio 2") })
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "radio 2")

            RadioButton(
                selected = selected == "radio 3",
                onClick = { onItemSelected("radio 3") })
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "radio 3")

            RadioButton(
                selected = selected == "radio 4",
                onClick = { onItemSelected("radio 4") })
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "radio 4")
        }
    }
}

@Composable
fun MyRadioButton() {
    Row(Modifier.fillMaxWidth()) {
        RadioButton(
            selected = false,
            onClick = {},
            enabled = true,
            colors = RadioButtonDefaults.colors(
                selectedColor = Color.Red,
                unselectedColor = Color.Yellow,
                disabledSelectedColor = Color.Black
            )
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = "Ejemplo")
    }
}

@Composable
fun MyTriStatusCheckBox() {
    // 3 estados
    // Activo, Inactivo, o indeterminado.
    var status by rememberSaveable {
        mutableStateOf(ToggleableState.Off)
    }
    TriStateCheckbox(state = status, onClick = {
        status = when (status) {
            ToggleableState.Off -> ToggleableState.Indeterminate
            ToggleableState.On -> ToggleableState.Off
            ToggleableState.Indeterminate -> ToggleableState.On
        }
    })

}

@Composable
fun getOptions(titles: List<String>): List<CheckInfo> {
    return titles.map { title ->
        var status by rememberSaveable { mutableStateOf(false) }
        CheckInfo(
            title,
            selected = status,
            onCheckedChange = {
                status = it
            })
    }
}

@Composable
fun MyCheckBoxPersonalized(checkInfo: CheckInfo) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Checkbox(
            checked = checkInfo.selected,
            onCheckedChange = { checkInfo.onCheckedChange(!checkInfo.selected) }
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(text = checkInfo.title)
    }
}

@Composable
fun MyCheckBox() {
    var state by remember {
        mutableStateOf(false)
    }
    // Checkbox con Texto
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = state,
            onCheckedChange = { state = !state },
            enabled = true, // Mismos atributos que Switch
            colors = CheckboxDefaults.colors(
                checkedColor = Color.Red,
                uncheckedColor = Color.Yellow,
                checkmarkColor = Color.Blue
            )
        )
        Spacer(modifier = Modifier.width(8.dp))

        Text(text = "Aceptar")
    }

}

@Composable
fun MySwitch() {
    // Interruptor de prendido y apagado.
    var estado by remember {
        mutableStateOf(false)
    }
    Switch(
        checked = estado,
        onCheckedChange = { estado = !estado },
        enabled = false,
        colors = SwitchDefaults.colors(
            uncheckedThumbColor = Color.Red,
            uncheckedTrackColor = Color.Cyan,
            checkedThumbColor = Color.Green,
            checkedTrackColor = Color.Magenta,
            disabledCheckedThumbColor = Color.Yellow,
            disabledUncheckedThumbColor = Color.Black
        )
    )
}