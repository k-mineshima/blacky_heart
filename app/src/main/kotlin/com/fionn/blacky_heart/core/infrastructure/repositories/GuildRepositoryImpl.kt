package com.fionn.blacky_heart.core.infrastructure.repositories

import com.fionn.blacky_heart.core.domain.entities.Guild
import com.fionn.blacky_heart.core.domain.repositories.GuildRepository
import com.fionn.blacky_heart.core.infrastructure.entities.GuildEntity
import com.fionn.blacky_heart.core.infrastructure.tables.GuildsTable
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.jdbc.deleteWhere

class GuildRepositoryImpl: GuildRepository {
    override fun save(guild: Guild) {
        GuildEntity.new {
            this.guildId = guild.id
            this.name = guild.name
            this.prefix = guild.prefix
            this.primaryChannelId = guild.primaryChannelId
        }
    }

    override fun delete(guildId: Long): Boolean {
        return GuildsTable.deleteWhere { GuildsTable.guildId eq guildId } > 0
    }
}
