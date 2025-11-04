package com.fionn.blacky_heart.core.domain.message

interface MessageClient {
    fun send(guildId: Long, channelId: Long, message: String)
}
