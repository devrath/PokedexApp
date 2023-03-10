package com.istudio.pokedex.ui.screens.pokemon_detail.composables.pokemon_body.name

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.istudio.pokedex.ui.theme.Nunito
import com.istudio.pokedex.util.PokemonUtils.capitalizeText

@Composable
fun PokemonName(
    id: Int, name: String
) {

    val pokemonName = capitalizeText(name)

    Text(
        text = "#${id} $pokemonName",
        fontSize = 30.sp,
        fontFamily = Nunito,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.onSurface
    )
}

@Preview
@Composable
private fun CurrentScreen() {
    PokemonName(2, "Ivysaur")
}