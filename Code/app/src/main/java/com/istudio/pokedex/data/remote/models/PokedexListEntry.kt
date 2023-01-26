package com.istudio.pokedex.data.remote.models

import androidx.compose.ui.graphics.Color

data class PokedexListEntry(
    val pokemonName: String,
    val imageUrl: String,
    val number: Int,
    var dominentColor: Color = Color.Transparent
)
