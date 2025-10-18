package com.fionn.blacky_heart.config.properties

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Discordbot(
    val token: String,
    @SerialName("default-prefix")
    val defaultPrefix: String,
    @SerialName("owner-id")
    val ownerId: String,
    val emojis: Emoji,
)
