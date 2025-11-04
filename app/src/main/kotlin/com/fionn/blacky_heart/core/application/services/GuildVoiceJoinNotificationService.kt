package com.fionn.blacky_heart.core.application.services

import com.fionn.blacky_heart.core.application.dtos.GuildVoiceJoinNotificationDto
import com.fionn.blacky_heart.core.application.transaction.TransactionScope
import com.fionn.blacky_heart.core.domain.entities.Guild
import com.fionn.blacky_heart.core.domain.entities.GuildVoiceJoinNotificationCondition
import com.fionn.blacky_heart.core.domain.message.MessageClient
import com.fionn.blacky_heart.core.domain.policies.GuildVoiceJoinNotificationPolicy
import com.fionn.blacky_heart.core.domain.repositories.GuildRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory

private val logger: Logger = LoggerFactory.getLogger(GuildVoiceJoinNotificationService::class.java)

class GuildVoiceJoinNotificationService(
    private val transactionScope: TransactionScope,
    private val guildRepository: GuildRepository,
    private val messageClient: MessageClient,
    private val guildVoiceJoinNotificationPolicy: GuildVoiceJoinNotificationPolicy,
) {
    fun execute(guildVoiceJoinNotificationDto: GuildVoiceJoinNotificationDto) {
        val guild: Guild =
            this.transactionScope.transaction {
                this.guildRepository.getByGuildId(guildVoiceJoinNotificationDto.guildId)
            } ?: let {
                logger.error("Notification failed: guild not found. guildId = ${guildVoiceJoinNotificationDto.guildId}")
                return
            }

        guild.primaryChannelId ?: let {
            logger.debug("Notification skipped: No primary channel id found for guild. guildId = {}", guild.primaryChannelId)
            return
        }

        val condition =
            GuildVoiceJoinNotificationCondition(
                isNewJoin = guildVoiceJoinNotificationDto.isNewJoin,
                isVoiceChannel = guildVoiceJoinNotificationDto.isVoiceChannel,
                isSoloInChannel = guildVoiceJoinNotificationDto.isSoloInChannel,
            )

        if (!guildVoiceJoinNotificationPolicy.shouldNotify(condition)) {
            logger.debug("Notification skipped: conditions not met. condition = {}", condition)
            return
        }

        val message =
            "${guildVoiceJoinNotificationDto.memberAsMention} が ${guildVoiceJoinNotificationDto.channelAsMention} でボイスチャットを始めました！"

        this.messageClient.send(
            guildId = guildVoiceJoinNotificationDto.guildId,
            channelId = guild.primaryChannelId,
            message = message,
        )
    }
}
