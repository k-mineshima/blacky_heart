package com.fionn.blacky_heart.core.infrastructure.message

import com.fionn.blacky_heart.core.domain.message.MessageClient
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel
import org.slf4j.Logger
import org.slf4j.LoggerFactory

private val logger: Logger = LoggerFactory.getLogger(DiscordMessageClient::class.java)

class DiscordMessageClient(
    private val jda: JDA
): MessageClient {
    override fun send(guildId: Long, channelId: Long, message: String) {
        val guild: Guild =
            this.jda.getGuildById(guildId) ?: let {
                logger.error("failed to send text message. guild not found. guildId = $guildId")
                return
            }

        val channel: TextChannel =
            guild.getTextChannelById(channelId) ?: let {
                logger.error("failed to send text message. channel not found. channelId = '$channelId'")
                return
            }

        channel.sendMessage(message).queue()
    }
}
