package com.github.dach83.oicochat.presentation.chat.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.dach83.oicochat.domain.model.Quote
import com.github.dach83.oicochat.domain.model.QuoteAlign

@Composable
fun QuoteCard(
    quote: Quote,
    onQuoteClick: (Quote) -> Unit
) {
    Row(
        horizontalArrangement = quote.horizontalArrangement,
        modifier = Modifier.fillMaxWidth()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(.6f)
                .padding(8.dp)
                .clickable { onQuoteClick(quote) }
        ) {
            Text(
                text = quote.text,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

private val Quote.horizontalArrangement: Arrangement.Horizontal
    get() = if (align == QuoteAlign.LEFT) {
        Arrangement.Start
    } else {
        Arrangement.End
    }
