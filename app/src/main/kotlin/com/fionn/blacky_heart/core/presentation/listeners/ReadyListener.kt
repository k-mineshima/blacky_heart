package com.fionn.blacky_heart.core.presentation.listeners

import com.fionn.blacky_heart.core.application.dtos.GuildDto
import com.fionn.blacky_heart.core.application.services.GuildSyncService
import net.dv8tion.jda.api.events.session.ReadyEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.slf4j.Logger
import org.slf4j.LoggerFactory

private val logger: Logger = LoggerFactory.getLogger(ReadyListener::class.java)

class ReadyListener(
    private val guildSyncService: GuildSyncService
): ListenerAdapter() {
    override fun onReady(event: ReadyEvent) {
        this.guildSyncService.execute(
            event.jda.guilds.map {
                GuildDto(
                    id = it.idLong,
                    name = it.name,
                    systemChannelId = it.systemChannel?.idLong,
                )
            }
        )

        logger.info("ready to use BOT!")
    }
}
