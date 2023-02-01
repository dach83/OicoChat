package com.github.dach83.oicochat.presentation.chat.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.dach83.oicochat.R
import com.github.dach83.oicochat.domain.exception.AppException

@Composable
fun ErrorMessageAndRefreshButton(
    cause: Throwable,
    onRefreshClick: () -> Unit
) {
    val errorMessage = if (cause is AppException) {
        stringResource(id = cause.friendlyMessage)
    } else {
        cause.message ?: stringResource(id = R.string.unknown_error)
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) { //
        Text(text = errorMessage)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onRefreshClick) {
            Text(text = stringResource(id = R.string.refresh))
        }
    }
}
