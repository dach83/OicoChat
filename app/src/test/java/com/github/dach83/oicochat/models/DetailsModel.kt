package com.github.dach83.oicochat.models

import com.github.dach83.oicochat.domain.model.Details
import com.github.dach83.oicochat.domain.model.TagColor

val testDetails = Details(
    id = 2,
    text = "Я собираюсь сделать ему предложение, от которого он не сможет отказаться",
    createdAt = "2019-03-11",
    tags = listOf(
        "android",
        "iOS",
        "MacOS",
        "Ubuntu",
        "Centos",
        "Solaris",
        "Windows",
        "IpadOS"
    ),
    colors = listOf(
        TagColor.YELLOW,
        TagColor.CYAN,
        TagColor.LTGRAY
    )
)
