package com.fionn.blacky_heart

import com.fionn.blacky_heart.config.Configuration
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent

class BlackyHeart(
    private val config: Configuration = Configuration.load()
) {
    fun start() {
        val jda: JDA = JDABuilder.createDefault(this.config.discordbot.token)
                                 .enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_VOICE_STATES)
                                 .addEventListeners()
                                 .build()
    }
}
