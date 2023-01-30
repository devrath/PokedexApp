package com.istudio.pokedex.ui.screens.pokemon_detail.composables.pokemon_body.stats

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.istudio.pokedex.R
import com.istudio.pokedex.data.remote.responses.Pokemon
import com.istudio.pokedex.ui.theme.Nunito
import com.istudio.pokedex.util.parseStatToAbbr
import com.istudio.pokedex.util.parseStatToColor

@Composable
fun PokemonBaseStats(
    pokemonInfo: Pokemon,
    animDelayPerItem: Int = 100
) {
    val maxBaseStat = remember {
        pokemonInfo.stats.maxOf { it.baseStat }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.str_base_stats),
            fontSize = 20.sp,
            fontFamily = Nunito,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onSurface
        )
        Spacer(modifier = Modifier.height(10.dp))

        for(i in pokemonInfo.stats.indices) {
            val stat = pokemonInfo.stats[i]
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = parseStatToAbbr(stat),
                        fontFamily = Nunito,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colors.onSurface
                    )
                    Spacer(modifier = Modifier.width(30.dp))
                    Text(
                        text = stat.baseStat.toString(),
                        fontWeight = FontWeight.Bold,
                        fontFamily = Nunito,
                        color = MaterialTheme.colors.onSurface
                    )
                }
                
                Spacer(modifier = Modifier.width(15.dp))

                Row(modifier = Modifier.weight(1.8f)) {
                    PokemonStat(
                        statName = parseStatToAbbr(stat),
                        statValue = stat.baseStat,
                        statMaxValue = maxBaseStat,
                        statColor = parseStatToColor(stat),
                        animDelay = i * animDelayPerItem
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}