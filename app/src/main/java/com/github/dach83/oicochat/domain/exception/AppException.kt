package com.github.dach83.oicochat.domain.exception

import androidx.annotation.StringRes

class AppException(
    @StringRes val friendlyMessage: Int, // user friendly error message
    cause: Throwable? = null
) : Exception(cause)
