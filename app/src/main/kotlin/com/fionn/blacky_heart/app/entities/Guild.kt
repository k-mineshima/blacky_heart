package com.fionn.blacky_heart.app.entities

import com.fionn.blacky_heart.app.entities.tables.GuildsTable
import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.LongEntity
import org.jetbrains.exposed.v1.dao.LongEntityClass

class Guild(id: EntityID<Long>): LongEntity(id) {
    companion object: LongEntityClass<Guild>(GuildsTable)

    var guildId: Long by GuildsTable.guildId
    var name: String by GuildsTable.name
    var prefix: String by GuildsTable.prefix
    var primaryChannelId: Long? by GuildsTable.primaryChannelId
    var createdAt: LocalDateTime by GuildsTable.createdAt
    var updatedAt: LocalDateTime by GuildsTable.updatedAt
}
