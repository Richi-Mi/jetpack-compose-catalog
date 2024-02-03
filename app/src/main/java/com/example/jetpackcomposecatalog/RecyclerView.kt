package com.example.jetpackcomposecatalog

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposecatalog.model.SuperHero
import kotlinx.coroutines.launch

@Composable
fun SimpleRecyclerView() {
    val myList = listOf("Richi", "Pepe", "Tetutle")
    // LazyRow(content = ) Recycler View horizontal
    LazyColumn {
        item {
            Text(text = "Hola 1")
        }
        item {
            Text(text = "Hola 2")
        }
        items(count = 7) {
            // El contenido se repite 7 veces.
            Text(text = "Este es el item $it")
        }
        items(myList) {
            Text(text = "Hola me llamo $it")
        }
    }
}

@Composable
fun SuperHeroView() {
    val context = LocalContext.current
    // Arrangement.spacedBy(8.dp ) - Espacio entre cada elemento de 8dp
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(getSuperHeroes()) { superhero ->
            ItemHero(superHero = superhero) {
                Toast.makeText(context, it.realName, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SuperHeroStickyView() {
    val context = LocalContext.current
    val superhero: Map<String, List<SuperHero>> = getSuperHeroes().groupBy { it.publisher }
    // Arrangement.spacedBy(8.dp ) - Espacio entre cada elemento de 8dp
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        superhero.forEach { (pubisher, mysuperHero) ->
            
            stickyHeader { 
                Text(text = pubisher, modifier = Modifier.fillMaxWidth().background(Color.Blue), color = Color.White)
            }
            
            items(mysuperHero) {
                ItemHero(superHero = it) {
                    Toast.makeText(context, it.realName, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
@Composable
fun SuperHeroViewWithControlView() {
    val context = LocalContext.current
    val rvState = rememberLazyListState() // Controla el estado del recyclerView
    val coroutineScope = rememberCoroutineScope()
    Column {
        LazyColumn(
            state = rvState,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            items( getSuperHeroes() ) { superhero ->
                ItemHero(superHero = superhero) {
                    Toast.makeText(context, it.realName, Toast.LENGTH_SHORT).show()
                }
            }
        }
        val showBtn by remember {
            derivedStateOf {
                rvState.firstVisibleItemIndex > 0 // Si se deja de ver el primer item ->
            }
        }
        if( showBtn ) {
            Button(onClick = {
                coroutineScope.launch {
                    rvState.animateScrollToItem(0)
                }
            },
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp) ) {
                Text(text = "Iam a fucking button")
            }
        }
    }
}

@Composable
fun SuperHeroGridView() {
    val context = LocalContext.current
    // GridCells.Fixed(2) - Adapata el tamaño de los items a 2 columnas
    // GridCells.Adaptive(100.dp)- Adapta cada componente a minimo 100.dp
    LazyVerticalGrid(
        columns = GridCells.Fixed(2) ,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        content = {
            items(getSuperHeroes()) { superhero ->
                ItemHero(superHero = superhero) {
                    Toast.makeText(context, it.realName, Toast.LENGTH_SHORT).show()
                }
            }
        },
        contentPadding = PaddingValues( horizontal = 16.dp, vertical = 16.dp) // Padding por fuera del reycler
    )
}

@Composable
fun ItemHero(superHero: SuperHero, onItemSelected: (SuperHero) -> Unit) {
    Card(
        modifier = Modifier
            // .width(200.dp)
            .fillMaxWidth() // Controlar dezplazamiento
            .clickable { onItemSelected(superHero) },
        border = BorderStroke(2.dp, Color.Red)
    ) {
        Column {
            Image(
                painter = painterResource(id = superHero.photo),
                contentDescription = "Avatar",
                Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = superHero.superheroName,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = superHero.realName,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = superHero.publisher,
                fontSize = 10.sp,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(8.dp)
            )
        }
    }
}

fun getSuperHeroes(): List<SuperHero> {
    return listOf(
        SuperHero("Spiderman", "Petter Parker", "Marvel", R.drawable.spiderman),
        SuperHero("Wolverine", "James Howlett", "Marvel", R.drawable.logan),
        SuperHero("Batman", "Bruce Wayne", "DC", R.drawable.batman),
        SuperHero("Thor", "Thor Odinson", "Marvel", R.drawable.thor),
        SuperHero("Flash", "Jay Garrick", "DC", R.drawable.flash),
        SuperHero("Green Lantern", "Alan Scott", "DC", R.drawable.green_lantern),
        SuperHero("Wonder Woman", "Princess Diana", "DC", R.drawable.wonder_woman)
    )
}