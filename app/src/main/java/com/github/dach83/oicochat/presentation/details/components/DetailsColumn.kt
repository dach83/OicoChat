package com.github.dach83.oicochat.presentation.details

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
            val backgroundColor = details.colors.getOrElse(ind) {
                TagColor.LTGRAY
            }.toColor()

            Text(
                text = details.tags[ind],
                modifier = Modifier
                    .clip(MaterialTheme.shapes.extraLarge)
                    .background(backgroundColor)
                    .padding(vertical = 8.dp, horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

private fun TagColor.toColor(): Color = when (this) {
    TagColor.GREEN -> Color.Green
    TagColor.RED -> Color.Red
    TagColor.MAGENTA -> Color.Magenta
    TagColor.DKGRAY -> Color.DarkGray
    TagColor.BLACK -> Color.Black
    TagColor.YELLOW -> Color.Yellow
    TagColor.CYAN -> Color.Cyan
    TagColor.LTGRAY -> Color.LightGray
}
