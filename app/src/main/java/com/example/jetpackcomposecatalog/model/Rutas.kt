package com.example.jetpackcomposecatalog.model

sealed class Rutas( val route : String ) {
    object Pantalla1: Rutas("pantalla1")
    object Pantalla2: Rutas("pantalla2")
    object Pantalla3: Rutas("pantalla3")
    object Pantalla4: Rutas("pantalla4/{age}") {
        fun createRoot( age: Int ) = "pantalla4/$age"
    }
    object Pantalla5: Rutas("pantalla5?name={name}") {
        fun createRoot( name: String ) = "pantalla5?name=$name"
    }
}