package com.fionn.blacky_heart.app.listeners

import com.fionn.blacky_heart.app.entities.GuildEntity
import net.dv8tion.jda.api.events.guild.GuildJoinEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

class GuildJoinListener(
    private val defaultPrefix: String
): ListenerAdapter() {
    override fun onGuildJoin(event: GuildJoinEvent) {
        transaction {
            GuildEntity.new {
                guildId = event.guild.idLong
                name = event.guild.name
                prefix = defaultPrefix
                primaryChannelId = event.guild.systemChannel?.idLong
            }
        }
    }
}
