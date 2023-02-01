package com.github.dach83.oicochat.presentation.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.github.dach83.oicochat.domain.model.Details
import com.github.dach83.oicochat.domain.model.TagColor

@Composable
fun DetailsColumn(details: Details) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = details.text,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = details.createdAt)
        Spacer(modifier = Modifier.height(16.dp))
        for (ind in details.tags.indices) {
            val (backgroundColor, textColor) = details.colors.getOrElse(ind) {
                TagColor.LTGRAY
            }.toColorsPair()

            Text(
                text = details.tags[ind],
                color = textColor,
                modifier = Modifier
                    .clip(MaterialTheme.shapes.extraLarge)
                    .background(backgroundColor)
                    .padding(vertical = 8.dp, horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

private fun TagColor.toColorsPair(): Pair<Color, Color> = when (this) {
    TagColor.GREEN -> Color.Green to Color.Black
    TagColor.RED -> Color.Red to Color.Black
    TagColor.MAGENTA -> Color.Magenta to Color.Black
    TagColor.DKGRAY -> Color.DarkGray to Color.White
    TagColor.BLACK -> Color.Black to Color.White
    TagColor.YELLOW -> Color.Yellow to Color.Black
    TagColor.CYAN -> Color.Cyan to Color.Black
    TagColor.LTGRAY -> Color.LightGray to Color.Black
}
