package com.fionn.blacky_heart.app.listeners

import com.fionn.blacky_heart.app.entities.GuildEntity
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.slf4j.Logger
import org.slf4j.LoggerFactory

private val logger: Logger = LoggerFactory.getLogger(GuildLeaveListener::class.java)

class GuildLeaveListener: ListenerAdapter() {
    override fun onGuildLeave(event: GuildLeaveEvent) {
        transaction {
            GuildEntity.findByGuildId(event.guild.idLong)?.delete() ?: logger.warn("could not delete guild: guild_id = ${event.guild.idLong}")
        }
    }
}
