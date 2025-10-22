package com.fionn.blacky_heart.core.infrastructure.repositories

import com.fionn.blacky_heart.core.domain.entities.Guild
import com.fionn.blacky_heart.core.domain.repositories.GuildRepository
import com.fionn.blacky_heart.core.infrastructure.entities.GuildEntity
import com.fionn.blacky_heart.core.infrastructure.tables.GuildsTable
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.core.inList
import org.jetbrains.exposed.v1.jdbc.batchInsert
import org.jetbrains.exposed.v1.jdbc.deleteWhere
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.update

class GuildRepositoryImpl: GuildRepository {
    override fun all(): List<Guild> {
        return GuildsTable.selectAll().toList().map {
            Guild(
                id = it[GuildsTable.guildId],
                name = it[GuildsTable.name],
                prefix = it[GuildsTable.prefix],
                primaryChannelId = it[GuildsTable.primaryChannelId],
            )
        }
    }

    override fun save(guild: Guild) {
        GuildEntity.new {
            this.guildId = guild.id
            this.name = guild.name
            this.prefix = guild.prefix
            this.primaryChannelId = guild.primaryChannelId
        }
    }

    override fun save(guilds: List<Guild>) {
        GuildsTable.batchInsert(guilds) {
            this[GuildsTable.guildId] = it.id
            this[GuildsTable.name] = it.name
            this[GuildsTable.prefix] = it.prefix
            this[GuildsTable.primaryChannelId] = it.primaryChannelId
        }
    }

    override fun update(guild: Guild) {
        GuildsTable.update({ GuildsTable.guildId eq guild.id }) {
            it[GuildsTable.name] = guild.name
            it[GuildsTable.prefix] = guild.prefix
            it[GuildsTable.primaryChannelId] = guild.primaryChannelId
        }
    }

    override fun delete(guildId: Long): Boolean {
        return GuildsTable.deleteWhere { GuildsTable.guildId eq guildId } > 0
    }

    override fun delete(guildIds: Collection<Long>): Int {
        return GuildsTable.deleteWhere { GuildsTable.guildId inList guildIds }
    }
}
