package com.fionn.blacky_heart.core.presentation.listeners

import com.fionn.blacky_heart.core.application.dtos.GuildDto
import com.fionn.blacky_heart.core.application.services.GuildDeleteService
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class GuildLeaveListener(
    private val guildDeleteService: GuildDeleteService
): ListenerAdapter() {
    override fun onGuildLeave(event: GuildLeaveEvent) {
        this.guildDeleteService.execute(
            GuildDto(
                id = event.guild.idLong,
                name = event.guild.name,
                systemChannelId = event.guild.systemChannel?.idLong,
            )
        )
    }
}
