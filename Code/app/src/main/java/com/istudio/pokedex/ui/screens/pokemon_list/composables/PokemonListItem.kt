package com.istudio.pokedex.ui.screens.pokemon_list.composables

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.palette.graphics.Palette
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.istudio.pokedex.R
import com.istudio.pokedex.data.remote.models.PokedexListEntry
import com.istudio.pokedex.ui.screens.pokemon_list.PokemonListVm
import com.istudio.pokedex.ui.theme.Roboto
import com.istudio.pokedex.ui.theme.RobotoCondensed
import com.istudio.pokedex.ui.theme.nunito

@Composable
fun PokemonListItem(
    item: PokedexListEntry,
    onItemClick: (PokedexListEntry) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PokemonListVm = hiltViewModel()
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
                //placeholder = painterResource(R.drawable.placeholder),
                contentDescription = item.pokemonName,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .align(CenterHorizontally),
                onSuccess = { success ->
                    val drawable = success.result.drawable
                    val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)
                    Palette.from(bmp).generate { palette ->
                        palette?.dominantSwatch?.rgb?.let { colorValue ->
                            pokemonDominantColor = Color(colorValue)
                        }
                    }
                }
            )
            Text(
                text = item.pokemonName,
                modifier = Modifier.fillMaxWidth(),
                color = Color.White,
                fontSize = 22.sp,
                fontFamily = nunito,
                fontWeight = FontWeight.Medium,
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Center
            )
        }
    }
}