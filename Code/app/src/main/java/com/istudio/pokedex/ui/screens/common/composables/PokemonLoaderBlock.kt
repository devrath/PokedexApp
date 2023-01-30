package com.istudio.pokedex.ui.screens.common.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun PokemonLoaderBlock(
    modifier: Modifier = Modifier,
    loaderSize : Dp = 100.dp
) {
    Box(
        modifier = modifier.padding(10.dp)
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colors.primary,
            modifier = modifier
                .size(loaderSize)
                .align(Alignment.Center)
        )
    }
}