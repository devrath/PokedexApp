package com.istudio.pokedex.ui.screens.pokemon_detail.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.istudio.pokedex.ui.theme.Nunito

@Composable
fun PokemonDetailDataItem(
    dataValue: String,
    dataTag: String,
    dataUnit: String,
    dataIcon: Painter,
    modifier: Modifier = Modifier
) {

    val iconSize : Dp = 50.dp
    val attributeSpacing : Dp = 8.dp
    val attributeValueSize = 18.sp
    val attributeTagSize = 12.sp
    val displayText = "$dataValue $dataUnit"

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Icon(
            painter = dataIcon,
            contentDescription = null,
            modifier = Modifier.size(iconSize),
            tint = MaterialTheme.colors.onSurface
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = displayText,
                fontFamily = Nunito,
                fontWeight = FontWeight.Bold,
                fontSize = attributeValueSize,
                color = MaterialTheme.colors.onSurface
            )
            Text(
                text = dataTag,
                fontFamily = Nunito,
                fontWeight = FontWeight.Normal,
                fontSize = attributeTagSize,
                color = MaterialTheme.colors.onSurface
            )
        }

    }
}