package com.fionn.blacky_heart

import com.fionn.blacky_heart.core.presentation.listeners.GuildJoinListener
import com.fionn.blacky_heart.core.presentation.listeners.GuildLeaveListener
import com.fionn.blacky_heart.config.Configuration
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent
import org.jetbrains.exposed.v1.jdbc.Database

class BlackyHeart(
    private val config: Configuration,
    private val guildJoinListener: GuildJoinListener,
    private val guildLeaveListener: GuildLeaveListener,
) {
    init {
        this.connectDatabase()
    }

    fun start() {
        val jda: JDA = JDABuilder.createDefault(this.config.discordbot.token)
                                 .enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_VOICE_STATES)
                                 .addEventListeners(
                                     guildJoinListener,
                                     guildLeaveListener,
                                 )
                                 .build()
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
