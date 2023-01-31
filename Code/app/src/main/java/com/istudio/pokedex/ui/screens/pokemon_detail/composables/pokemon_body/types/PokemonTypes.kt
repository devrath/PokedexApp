package com.istudio.pokedex.ui.screens.pokemon_detail.composables.pokemon_body

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.istudio.pokedex.data.remote.responses.Type
import com.istudio.pokedex.data.remote.responses.TypeX
import com.istudio.pokedex.ui.theme.Nunito
import com.istudio.pokedex.util.PokemonUtils
import com.istudio.pokedex.util.parseTypeToColor

@Composable
fun PokemonTypes(types: List<Type>) {
    val viewOuterPadding = 16.dp
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(viewOuterPadding)
    ) {

        val weight = 1f
        val viewInnerPadding = 8.dp
        val viewHeight = 35.dp
        val viewFontSize = 18.sp
        val textColor = Color.White

        for (type in types) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(weight)
                    .padding(horizontal = viewInnerPadding)
                    .clip(CircleShape)
                    .background(parseTypeToColor(type))
                    .height(viewHeight)
            ) {
                val pokemonTypeName = PokemonUtils.capitalizeText(type.type.name)
                Text(
                    text = pokemonTypeName,
                    color = textColor,
                    fontSize = viewFontSize,
                    fontFamily = Nunito
                )
            }
        }
    }
}

@Composable @Preview
private fun CurrentScreen() {
    PokemonTypes(
        listOf(
            Type(slot = 1, type = TypeX(name = "fighting", url = "")),
            Type(slot = 2, type = TypeX(name = "fairy", url = ""))
        )
    )
}