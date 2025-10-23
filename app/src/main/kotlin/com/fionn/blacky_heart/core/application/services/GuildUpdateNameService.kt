package com.fionn.blacky_heart.core.application.services

import com.fionn.blacky_heart.core.application.dtos.GuildDto
import com.fionn.blacky_heart.core.application.transaction.TransactionScope
import com.fionn.blacky_heart.core.domain.repositories.GuildRepository

class GuildUpdateNameService(
    private val transactionScope: TransactionScope,
    private val guildRepository: GuildRepository,
) {
    fun execute(guild: GuildDto) {
        this.transactionScope.transaction {
            this.guildRepository.updateName(guild.id, guild.name)
        }
    }
}
