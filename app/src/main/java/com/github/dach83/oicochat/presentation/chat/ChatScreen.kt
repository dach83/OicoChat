package com.github.dach83.oicochat.presentation.chat

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.github.dach83.oicochat.presentation.destinations.DetailsScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@RootNavGraph(start = true)
@Destination
@Composable
fun ChatScreen(
    viewModel: ChatViewModel = koinViewModel(),
    navigator: DestinationsNavigator
) {
    Button(onClick = {
        navigator.navigate(DetailsScreenDestination())
    }) {
        Text(text = "Chat screen")
    }
}
