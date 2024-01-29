package com.example.jetpackcomposecatalog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun ConstraintLayoutExample() {
    ConstraintLayout( modifier = Modifier.fillMaxSize()) {

        val ( boxRed, boxBlue, boxYellow, boxMagenta, boxGreen ) = createRefs()

        // constraintAs - Id de referencia
        // create Refs = Indicamos que variables usaremos como referencia

        Box( modifier = Modifier
            .width(80.dp)
            .height(80.dp)
            .background(Color.Red)
            .constrainAs(boxRed) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            } )
        Box( modifier = Modifier
            .width(80.dp)
            .height(80.dp)
            .background(Color.Blue)
            .constrainAs(boxBlue) {
                top.linkTo(boxRed.bottom)
                start.linkTo(boxRed.end)
            } )
        Box( modifier = Modifier
            .width(80.dp)
            .height(80.dp)
            .background(Color.Yellow)
            .constrainAs(boxYellow) {
                end.linkTo(boxRed.start)
                bottom.linkTo(boxRed.top)
            } )
        Box( modifier = Modifier
            .width(80.dp)
            .height(80.dp)
            .background(Color.Magenta)
            .constrainAs(boxMagenta) {
                start.linkTo(boxRed.end)
                bottom.linkTo(boxRed.top)
            } )

        Box(modifier = Modifier
            .width(80.dp)
            .height(80.dp)
            .background(Color.Green)
            .constrainAs(boxGreen) {
                end.linkTo(boxRed.start)
                top.linkTo(boxRed.bottom)
            })

    }
}

// Constraint Layout avanzado

// Guidelines
@Composable
fun ConstraintGuidelines() {
    ConstraintLayout( Modifier.fillMaxSize() ) {

        // val topGuide = createGuidelineFromTop( 20.dp )

        val boxRed = createRef() // Creamos una referencia
        val topGuide = createGuidelineFromTop( 0.1f )
        val startGuide = createGuidelineFromStart( 0.25f )

        Box(
            modifier = Modifier
                .size(96.dp)
                .background(Color.Red)
                .constrainAs(boxRed) {
                    start.linkTo(startGuide)
                    top.linkTo(topGuide)
                }
        )
    }
}

// Barreras, linea invisible que no permite a otros componentes atravesar un espacio

@Composable
fun ConstraintBarrers() {
    ConstraintLayout( Modifier.fillMaxSize() ) {

        val ( boxRed, boxBlue, boxYellow ) = createRefs()
        val barrerEnd = createEndBarrier( boxRed, boxBlue ) // Creamos una barrera en el final de los 2 componentes.

        Box( modifier = Modifier
            .size(80.dp)
            .background(Color.Red)
            .constrainAs(boxRed) {
                start.linkTo(parent.start, margin = 16.dp)
                top.linkTo(parent.top)
            } )
        Box( modifier = Modifier
            .size(120.dp)
            .background(Color.Blue)
            .constrainAs(boxBlue) {
                start.linkTo(parent.start, margin = 32.dp)
                top.linkTo(boxRed.bottom, margin = 8.dp)
            } )

        Box(modifier = Modifier
            .background(Color.Yellow)
            .size(40.dp)
            .constrainAs(boxYellow) {
                start.linkTo(barrerEnd)
                top.linkTo(parent.top, margin = 12.dp)
            })
    }
}

// Cadenas o Chains.
@Preview
@Composable
fun ConstraintChainExample() {
    ConstraintLayout ( Modifier.fillMaxSize() ) {
        val ( boxRed, boxBlue, boxYellow ) = createRefs()

        Box( modifier = Modifier
            .size(64.dp)
            .background(Color.Red)
            .constrainAs(boxRed) {
                start.linkTo( parent.start )
                end.linkTo( boxBlue.start )
            } )
        Box( modifier = Modifier
            .size(64.dp)
            .background(Color.Blue)
            .constrainAs(boxBlue) {
                start.linkTo( boxRed.end )
                end.linkTo( boxYellow.start )
            } )

        Box(modifier = Modifier
            .background(Color.Yellow)
            .size(64.dp)
            .constrainAs(boxYellow) {
                start.linkTo( boxBlue.end )
                end.linkTo( parent.end )
            })

        // Packed. - Lo mas junto posible
        // Spread .- Separados por igual
        // SpreadInside .- Separados lo mas posible.
        createHorizontalChain( boxBlue, boxRed, boxYellow, chainStyle = ChainStyle.SpreadInside )
    }
}