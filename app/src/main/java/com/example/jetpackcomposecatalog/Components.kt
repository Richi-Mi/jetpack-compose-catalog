package com.example.jetpackcomposecatalog

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun ScaffoldExample() {
    // Scaffold es un componente esqueleto que nos permite agregar otros
    // componentes de Material Design como ToolBar, NavBar, etc.
    // Es obligatorio meter un padding al primer componente del scaffold
    // Este padding es el espacio que ocupan la TopBar, Footer, etc.

    // Para usar el snackbar necesitamos guardar el estdo del scafold

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { MyMenu() }
    ) {

        Scaffold(
            topBar = {
                MyTopAppBar() {
                    scope.launch {
                        snackbarHostState.showSnackbar( it )
                    }
                }
            }, // Agrega la top bar a la pantalla
            snackbarHost = {
                SnackbarHost( hostState = snackbarHostState )
            },
            bottomBar = { MyBottomNavigation() },
            floatingActionButton = { MyFAB() {
                scope.launch {
                    drawerState.apply {
                        if( isClosed ) open() else close()
                    }
                }
            } },
            floatingActionButtonPosition = FabPosition.Center // .End
        ) { contentPadding ->
            Box( modifier = Modifier.padding( contentPadding )) {
                // Snackbar
                Button(onClick = {
                    scope.launch {

                        // snackbarHostState.showSnackbar("Snackbar") - Snackbar Basico
                        val result = snackbarHostState
                            .showSnackbar(
                                message = "Notificación",
                                actionLabel = "Aceptar",
                                // Defaults to SnackbarDuration.Short
                                duration = SnackbarDuration.Short
                            )
                        when (result) {
                            SnackbarResult.ActionPerformed -> {
                                Toast.makeText( context, "Aceptar Pulsado", Toast.LENGTH_SHORT ).show()
                            }
                            SnackbarResult.Dismissed -> {
                                Toast.makeText( context, "No Pulsado", Toast.LENGTH_SHORT ).show()
                            }

                            else -> {}
                        }
                    }
                }) {
                    Text(text = "Show Snackbar")
                }
            }
        }

    }
}
@Composable
fun MyBottomNavigation() {
    var index by remember {
        mutableStateOf( 0 )
    }
    NavigationBar(
        contentColor = MaterialTheme.colorScheme.primaryContainer
    ) {
        NavigationBarItem(
            selected = index == 1,
            onClick = { index = 1 },
            icon = { Icon( imageVector = Icons.Filled.Home, contentDescription = "Home") },
            label = { Text(text = "Home")}
        )
        NavigationBarItem(
            selected = index == 2,
            onClick = { index = 2 },
            icon = { Icon( imageVector = Icons.Filled.Favorite, contentDescription = "Home") },
            label = { Text(text = "Heart")}
        )
        NavigationBarItem(
            selected = index == 3,
            onClick = { index = 3 },
            icon = { Icon( imageVector = Icons.Filled.Info, contentDescription = "Home") },
            label = { Text(text = "Info")}
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar( onClickIcon : (String) -> Unit ) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors( // Colores de la TopBar
            containerColor = Color.Red, // Color de fondo
            titleContentColor = Color.White, // Color del titulo
            navigationIconContentColor = Color.White,
            actionIconContentColor = Color.White,   //Color a todos los iconos
        ),
        modifier = Modifier.shadow(
            elevation = 24.dp,
            spotColor = Color.Red
        ),
        title = { Text(text = "MI primera tollbar") },
        navigationIcon = {
            IconButton(onClick = { onClickIcon("Regresar") }) {
                Icon(
                    imageVector = Icons.Filled.Menu, contentDescription = "Menu"
                )
            }
        },
        actions = {
            IconButton(onClick = { onClickIcon("Buscar")}) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search",
                    tint = Color.Yellow     //Pesonalizo solo el color de este icono
                )
            }
            IconButton(onClick = { onClickIcon("Cerrar") }) {
                Icon(
                    imageVector = Icons.Filled.Close, contentDescription = "Cerrar"
                )
            }
        }
    )
}
@Composable
fun MyMenu() {
    Column(
        modifier = Modifier.background( Color.White)
    ) {
        Text(text = "Primera Opción",
            Modifier
                .padding(vertical = 8.dp) )
        Text(text = "Segunda Opción",
            Modifier
                .padding(vertical = 8.dp) )
        Text(text = "Tercera Opción",
            Modifier
                .padding(vertical = 8.dp) )
        Text(text = "Cuarta Opción",
            Modifier
                .padding(vertical = 8.dp) )
    }
}

@Composable
fun MyFAB( onClick: () -> Unit ) {
    FloatingActionButton(onClick = { onClick() }, containerColor = Color.Cyan ) {
        Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Add")
    }
}