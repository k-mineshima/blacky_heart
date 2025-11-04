package com.fionn.blacky_heart.core.domain.entities

data class GuildVoiceJoinNotificationCondition(
    val isNewJoin: Boolean,
    val isVoiceChannel: Boolean,
    val isSoloInChannel: Boolean,
)
