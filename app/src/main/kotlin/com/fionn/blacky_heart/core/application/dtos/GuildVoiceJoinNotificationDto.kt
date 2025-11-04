package com.fionn.blacky_heart.core.application.dtos

data class GuildVoiceJoinNotificationDto(
    val guildId: Long,
    val isNewJoin: Boolean,
    val isVoiceChannel: Boolean,
    val isSoloInChannel: Boolean,
    val memberAsMention: String,
    val channelAsMention: String,
)
