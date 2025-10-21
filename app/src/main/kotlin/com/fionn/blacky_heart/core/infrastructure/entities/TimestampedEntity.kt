package com.fionn.blacky_heart.core.infrastructure.entities

import com.fionn.blacky_heart.core.infrastructure.tables.TimestampedTable
import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.LongEntity

abstract class TimestampedEntity(id: EntityID<Long>, table: TimestampedTable): LongEntity(id) {
    val createdAt: LocalDateTime by table.createdAt
    var updatedAt: LocalDateTime by table.updatedAt
}
