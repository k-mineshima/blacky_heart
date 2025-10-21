package com.fionn.blacky_heart.core.presentation.listeners

import com.fionn.blacky_heart.core.application.dtos.GuildDto
import com.fionn.blacky_heart.core.application.services.GuildRegisterService
import net.dv8tion.jda.api.events.guild.GuildJoinEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class GuildJoinListener(
    private val guildRegisterService: GuildRegisterService
): ListenerAdapter() {
    override fun onGuildJoin(event: GuildJoinEvent) {
        this.guildRegisterService.execute(
            GuildDto(
                id = event.guild.idLong,
                name = event.guild.name,
                systemChannelId = event.guild.systemChannel?.idLong
            )
        )
    }
}
