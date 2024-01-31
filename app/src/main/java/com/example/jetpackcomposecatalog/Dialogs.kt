package com.example.jetpackcomposecatalog

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun MyConfirmDialog( show: Boolean, onDissmiss: () -> Unit ) {
    if( show ) {
        val opts = listOf("Calculo Aplicado", "Mecanica", "Electromagnetismo", "Yanira", "Jurado")
        Dialog(
            onDismissRequest = { onDissmiss() }
        ) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                MyTitleDialog(text = "Phone Ringstone")
                Divider( Modifier.fillMaxWidth(), color = Color.LightGray )

                var status by remember { mutableStateOf("") }
                MyRadioButtonList(selected = status, onItemSelected = { status = it })
                Divider( Modifier.fillMaxWidth(), color = Color.LightGray )

                Row( Modifier.align( Alignment.End )) {
                    TextButton(onClick = { }) {
                        Text(text = "Cancel")
                    }
                    TextButton(onClick = { }) {
                        Text(text = "Ok")
                    }
                }
            }
        }
    }
}
@Composable
fun OptionItem( name : String ) {
    Row( verticalAlignment = Alignment.CenterVertically ) {
        RadioButton(selected = false, onClick = {} )
        Text(text =  name )
    }
}
@Composable
fun MyAlertDialog( show: Boolean, onDissmiss: () -> Unit, onConfirm: () -> Unit ) {
    if( show ) {
        AlertDialog(
            onDismissRequest = { onDissmiss() }, // Cuando de click fuera del dialogo
            title = { Text(text = "Titulo") },
            text = { Text(text = "Hola soy una descripcion :(") },
            confirmButton = {
                TextButton(onClick = { onConfirm() }) {
                    Text(text = "Confirmar")
                }
            },
            dismissButton = {
                TextButton(onClick = { onDissmiss() }) {
                    Text(text = "Dissmiss Button")
                }
            }
        )
    }
}
@Composable
fun MyCustomDialog( show: Boolean, onDissmiss: () -> Unit ) {
    if( show ) {
        Dialog( onDismissRequest = { onDissmiss() } ) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                MyTitleDialog(text = "Set Backup Account")
                AccountItem(email = "ejemplo1@gmail.com", drawable = R.drawable.avatar1 )
                AccountItem(email = "ejemplo2@gmail.com", drawable = R.drawable.avatar2 )
                AccountItem(email = "Add Account", drawable = R.drawable.add )
            }
        }
    }
}
@Composable
fun AccountItem( email: String, @DrawableRes drawable: Int ) {
    Row( verticalAlignment = Alignment.CenterVertically ) {
        Image(
            painter = painterResource(id = drawable),
            contentDescription = "avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(48.dp)
                .clip(CircleShape) )
        Text( text = email, fontSize = 14.sp, color = Color.Gray, modifier = Modifier.padding( 20.dp ) )
    }

}
@Composable
fun MyTitleDialog( text: String, modifier: Modifier = Modifier.padding( 20.dp ) ) {
    Text( text = text,
    modifier = modifier,
        style = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
    ) )
}
@Composable
fun MySimpleCustomDialog( show: Boolean, onDissmiss: () -> Unit ) {
    if( show ) {
        Dialog(
            onDismissRequest = { onDissmiss() },
            properties = DialogProperties(
                dismissOnBackPress = false, // Cuando pulsan el boton para atras.
                dismissOnClickOutside = true ) // Cuando dan click afuera
        ) {
            Column(
                Modifier
                    .background(Color.White)
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Esto es un Ejemplo")
                Text(text = "Esto es un Ejemplo")
                Text(text = "Esto es un Ejemplo")
            }
        }
    }
}