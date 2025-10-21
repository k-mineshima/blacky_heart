package com.fionn.blacky_heart.core.application.dtos

data class GuildDto(
    val id: Long,
    val name: String,
    val systemChannelId: Long?,
)
