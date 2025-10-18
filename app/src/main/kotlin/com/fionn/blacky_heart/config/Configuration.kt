package com.fionn.blacky_heart.config

import com.fionn.blacky_heart.config.properties.Database
import com.fionn.blacky_heart.config.properties.Discordbot
import com.fionn.blacky_heart.exceptions.ConfigurationFileNotFoundException
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class Configuration(
    val discordbot: Discordbot,
    val database: Database,
) {
    companion object {
        fun load(): Configuration {
            val configFilePath = "/application.json"

            val configJsonText: String =
                this::class.java.getResource(configFilePath)?.readText() ?: throw ConfigurationFileNotFoundException()

            return Json.decodeFromString<Configuration>(configJsonText)
        }
    }
}
