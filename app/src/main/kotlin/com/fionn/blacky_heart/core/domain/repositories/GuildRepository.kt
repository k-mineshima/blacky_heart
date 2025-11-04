package com.fionn.blacky_heart.core.domain.repositories

import com.fionn.blacky_heart.core.domain.entities.Guild

interface GuildRepository {
    fun all(): List<Guild>

    fun getByGuildId(guildId: Long): Guild?

    fun save(guild: Guild)

    fun save(guilds: List<Guild>)

    fun update(guild: Guild)

    fun updateName(guildId: Long, name: String)

    fun delete(guildId: Long): Boolean

    fun delete(guildIds: Collection<Long>): Int
}
