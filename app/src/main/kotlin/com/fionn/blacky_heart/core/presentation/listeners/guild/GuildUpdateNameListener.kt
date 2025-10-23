package com.fionn.blacky_heart.core.presentation.listeners.guild

import com.fionn.blacky_heart.core.application.dtos.GuildDto
import com.fionn.blacky_heart.core.application.services.GuildUpdateNameService
import net.dv8tion.jda.api.events.guild.update.GuildUpdateNameEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class GuildUpdateNameListener(
    private val guildUpdateNameService: GuildUpdateNameService
): ListenerAdapter() {
    override fun onGuildUpdateName(event: GuildUpdateNameEvent) {
        this.guildUpdateNameService.execute(
            GuildDto(
                id = event.guild.idLong,
                name = event.guild.name,
                systemChannelId = null,
            )
        )
    }
}
