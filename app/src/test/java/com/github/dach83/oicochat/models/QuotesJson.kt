package com.github.dach83.oicochat.models

val quotesCorrectJson = """
    [
      {
        "id": 1,
        "createdBy": 0,
        "text": "Честно говоря, моя дорогая, мне наплевать"
      },
      {
        "id": 2,
        "createdBy": 1,
        "text": "Я собираюсь сделать ему предложение, от которого он не сможет отказаться"
      }
    ]
""".trimIndent()

const val quotesInvalidJson = "I'm not JSON"
