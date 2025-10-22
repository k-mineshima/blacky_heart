package com.fionn.blacky_heart.core.application.services

import com.fionn.blacky_heart.config.Configuration
import com.fionn.blacky_heart.core.application.dtos.GuildDto
import com.fionn.blacky_heart.core.application.transaction.TransactionScope
import com.fionn.blacky_heart.core.domain.entities.Guild
import com.fionn.blacky_heart.core.domain.repositories.GuildRepository

class GuildSyncService(
    private val config: Configuration,
    private val transactionScope: TransactionScope,
    private val guildRepository: GuildRepository,
) {
    fun execute(guilds: List<GuildDto>) {
        this.transactionScope.transaction {
            val storedGuilds: Map<Long, Guild> = this.guildRepository.all().associateBy(Guild::id)

            this.guildRepository.delete(
                storedGuilds.keys - guilds.map(GuildDto::id).toSet()
            )

            val (knownGuilds: List<GuildDto>, newGuilds: List<GuildDto>) = guilds.partition { it.id in storedGuilds.keys }

            this.guildRepository.save(
                newGuilds.map {
                    Guild(
                        id = it.id,
                        name = it.name,
                        prefix = this.config.discordbot.defaultPrefix,
                        primaryChannelId = it.systemChannelId,
                    )
                }
            )

            knownGuilds.forEach {
                val storedGuild: Guild = storedGuilds.getValue(it.id)

                if (it.name != storedGuild.name) {
                    this.guildRepository.update(
                        Guild(
                            id = it.id,
                            name = it.name,
                            prefix = storedGuild.prefix,
                            primaryChannelId = storedGuild.primaryChannelId,
                        )
                    )
                }
            }
        }
    }
}
