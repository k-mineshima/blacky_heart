package com.fionn.blacky_heart.core.application.services

import com.fionn.blacky_heart.config.Configuration
import com.fionn.blacky_heart.core.application.dtos.GuildDto
import com.fionn.blacky_heart.core.application.transaction.TransactionScope
import com.fionn.blacky_heart.core.domain.entities.Guild
import com.fionn.blacky_heart.core.domain.repositories.GuildRepository

class GuildRegisterService(
    private val config: Configuration,
    private val transactionScope: TransactionScope,
    private val guildRepository: GuildRepository,
) {
    fun execute(guild: GuildDto) {
        this.transactionScope.transaction {
            this.guildRepository.save(
                Guild(
                    guild.id,
                    guild.name,
                    this.config.discordbot.defaultPrefix,
                    guild.systemChannelId,
                )
            )
        }
    }
}
