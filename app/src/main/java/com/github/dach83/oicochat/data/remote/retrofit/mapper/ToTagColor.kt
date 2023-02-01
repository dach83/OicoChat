package com.github.dach83.oicochat.data.remote.retrofit.mapper

import com.github.dach83.oicochat.domain.model.TagColor

fun String.toTagColor(): TagColor = when (this) {
    "GREEN" -> TagColor.GREEN
    "RED" -> TagColor.RED
    "MAGENTA" -> TagColor.MAGENTA
    "DKGRAY" -> TagColor.DKGRAY
    "YELLOW" -> TagColor.YELLOW
    "CYAN" -> TagColor.CYAN
    "LTGRAY" -> TagColor.LTGRAY
    else -> TagColor.BLACK
}
