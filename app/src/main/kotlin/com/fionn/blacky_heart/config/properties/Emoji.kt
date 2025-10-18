package com.fionn.blacky_heart.config.properties

import kotlinx.serialization.Serializable

@Serializable
data class Emoji(
    val success: String,
    val warning: String,
    val error: String,
)
