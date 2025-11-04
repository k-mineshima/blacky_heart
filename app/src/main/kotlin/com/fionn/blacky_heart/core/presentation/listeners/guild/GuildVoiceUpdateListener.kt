package com.fionn.blacky_heart.core.presentation.listeners.guild

import com.fionn.blacky_heart.core.application.dtos.GuildVoiceJoinNotificationDto
import com.fionn.blacky_heart.core.application.services.GuildVoiceJoinNotificationService
import net.dv8tion.jda.api.entities.channel.ChannelType
import net.dv8tion.jda.api.entities.channel.unions.AudioChannelUnion
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class GuildVoiceUpdateListener(
    private val guildVoiceJoinNotificationService: GuildVoiceJoinNotificationService
): ListenerAdapter() {
    override fun onGuildVoiceUpdate(event: GuildVoiceUpdateEvent) {
        val channelJoined: AudioChannelUnion = event.channelJoined ?: return

        this.guildVoiceJoinNotificationService.execute(
            GuildVoiceJoinNotificationDto(
                guildId = event.guild.idLong,
                isNewJoin = event.channelLeft == null,
                isVoiceChannel = channelJoined.type == ChannelType.VOICE,
                isSoloInChannel = channelJoined.members.size == 1,
                memberAsMention = event.member.asMention,
                channelAsMention = channelJoined.asMention,
            )
        )
    }
}
