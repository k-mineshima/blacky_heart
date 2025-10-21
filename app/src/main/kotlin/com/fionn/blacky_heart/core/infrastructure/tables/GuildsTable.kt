package com.fionn.blacky_heart.core.infrastructure.tables

import org.jetbrains.exposed.v1.core.Column

object GuildsTable: TimestampedTable("guilds") {
    val guildId: Column<Long> = long("guild_id").uniqueIndex()
    val name: Column<String> = varchar("name", 255)
    val prefix: Column<String> = varchar("prefix", 255)
    val primaryChannelId: Column<Long?> = long("primary_channel_id").uniqueIndex().nullable()
}
