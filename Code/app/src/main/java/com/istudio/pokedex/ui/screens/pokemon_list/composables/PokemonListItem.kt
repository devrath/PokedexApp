package com.istudio.pokedex.ui.screens.pokemon_list.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.istudio.pokedex.R
import com.istudio.pokedex.data.remote.models.PokedexListEntry
import com.istudio.pokedex.ui.screens.pokemon_list.PokemonListVm
import com.istudio.pokedex.ui.theme.RobotoCondensed

@Composable
fun PokemonListItem(
    item: PokedexListEntry,
    onItemClick: (PokedexListEntry) -> Unit,
    viewModel: PokemonListVm = hiltViewModel(),
    modifier: Modifier = Modifier,
) {
    val defaultColor = Color.White
    var pokemonDominantColor by remember { mutableStateOf(defaultColor) }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(5.dp)
            .shadow(5.dp, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .background(pokemonDominantColor)
            .clickable {
                item.dominentColor = pokemonDominantColor
                onItemClick(item)
            },

        ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
        ) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = item.pokemonName,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .align(CenterHorizontally),
                onSuccess = { success ->
                    val drawable = success.result.drawable
                    viewModel.calcDominantColor(drawable){ pokemonDominantColor = it }
                }
            )
            Text(
                text = item.pokemonName,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 20.sp,
                fontFamily = RobotoCondensed,
                textAlign = TextAlign.Center
            )
        }
    }
}