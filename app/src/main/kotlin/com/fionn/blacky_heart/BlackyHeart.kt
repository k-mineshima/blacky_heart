package com.fionn.blacky_heart

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent

class BlackyHeart {
    fun start() {
        val jda: JDA = JDABuilder.createDefault("secret")
                                 .enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_VOICE_STATES)
                                 .addEventListeners()
                                 .build()
    }
}
