package com.fionn.blacky_heart

import com.fionn.blacky_heart.config.Configuration
import com.fionn.blacky_heart.core.presentation.listeners.guild.GuildJoinListener
import com.fionn.blacky_heart.core.presentation.listeners.guild.GuildLeaveListener
import com.fionn.blacky_heart.core.presentation.listeners.guild.GuildUpdateNameListener
import com.fionn.blacky_heart.core.presentation.listeners.guild.GuildVoiceUpdateListener
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import net.dv8tion.jda.api.JDA
import org.jetbrains.exposed.v1.jdbc.Database
import org.slf4j.Logger
import org.slf4j.LoggerFactory

private val logger: Logger = LoggerFactory.getLogger(BlackyHeart::class.java)

class BlackyHeart(
    private val config: Configuration,
    private val jda: JDA,
    private val guildJoinListener: GuildJoinListener,
    private val guildLeaveListener: GuildLeaveListener,
    private val guildUpdateNameListener: GuildUpdateNameListener,
    private val guildVoiceUpdateNameListener: GuildVoiceUpdateListener,
) {
    init {
        this.connectDatabase()

        this.jda.addEventListener(
            this.guildJoinListener,
            this.guildLeaveListener,
            this.guildUpdateNameListener,
            this.guildVoiceUpdateNameListener,
        )
        this.jda.awaitReady()
    }

    private fun connectDatabase() {
        val hikariConfig: HikariConfig = HikariConfig().also {
            it.jdbcUrl = this.config.database.getDatabaseUrl()
            it.driverClassName = this.config.database.driver
            it.username = this.config.database.user
            it.password = this.config.database.password
            it.maximumPoolSize = this.config.database.poolSize
        }

        Database.connect(HikariDataSource(hikariConfig))
    }
}
