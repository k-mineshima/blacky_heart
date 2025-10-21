package com.fionn.blacky_heart.core.application.services

import com.fionn.blacky_heart.core.application.dtos.GuildDto
import com.fionn.blacky_heart.core.application.transaction.TransactionScope
import com.fionn.blacky_heart.core.domain.repositories.GuildRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory

private val logger: Logger = LoggerFactory.getLogger(GuildDeleteService::class.java)

class GuildDeleteService(
    private val transactionScope: TransactionScope,
    private val guildRepository: GuildRepository,
) {
    fun execute(guild: GuildDto) {
        this.transactionScope.transaction {
            val isDeleted: Boolean = this.guildRepository.delete(guild.id)

            if (!isDeleted) {
                logger.warn("failed to delete guild. guild_id = ${guild.id}.")
            }
        }
    }
}
