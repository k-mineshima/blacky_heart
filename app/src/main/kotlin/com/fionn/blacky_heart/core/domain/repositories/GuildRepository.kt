package com.fionn.blacky_heart.core.domain.repositories

import com.fionn.blacky_heart.core.domain.entities.Guild

interface GuildRepository {
    fun save(guild: Guild)

    fun delete(guildId: Long): Boolean
}
