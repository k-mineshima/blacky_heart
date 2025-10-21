package com.fionn.blacky_heart.core.domain.entities

class Guild(
    val id: Long,
    val name: String,
    val prefix: String,
    val primaryChannelId: Long?,
)
