package com.fionn.blacky_heart.core.infrastructure.entities

import com.fionn.blacky_heart.core.infrastructure.tables.GuildsTable
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.dao.LongEntityClass

class GuildEntity(id: EntityID<Long>): TimestampedEntity(id, GuildsTable) {
    companion object: LongEntityClass<GuildEntity>(GuildsTable) {
        fun findByGuildId(guildId: Long): GuildEntity? {
            return find { GuildsTable.guildId eq guildId }.firstOrNull()
        }
    }

    var guildId: Long by GuildsTable.guildId
    var name: String by GuildsTable.name
    var prefix: String by GuildsTable.prefix
    var primaryChannelId: Long? by GuildsTable.primaryChannelId
}
