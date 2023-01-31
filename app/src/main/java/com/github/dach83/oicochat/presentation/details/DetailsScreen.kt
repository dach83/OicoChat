package com.github.dach83.oicochat.presentation.details

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun DetailsScreen(
    quoteId: Int,
    navigator: DestinationsNavigator
) {
    Box(contentAlignment = Alignment.Center) {
        Button(onClick = {
            navigator.popBackStack()
        }) {
            Text(text = "Details screen $quoteId")
        }
    }
}
