package com.istudio.pokedex.ui.screens.pokemon_detail.composables.header

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PokemonHeader(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Box(
        contentAlignment = Alignment.TopStart,
        modifier = modifier.background(
            Brush.verticalGradient(
                listOf(
                    Color.Black, Color.Transparent
                )
            )
        )
    ) {
        IconButton(onClick = onClick) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(36.dp)
                    .offset(16.dp, 16.dp)
            )
        }
    }
}

@Preview()
@Composable
private fun DefaultPreview() {
    Box(
        modifier = Modifier.fillMaxSize().background(Color.White)
    ) {
        PokemonHeader(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ){
            // On click is triggered
        }
    }
}