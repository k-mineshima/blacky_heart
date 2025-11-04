package com.fionn.blacky_heart.core.domain.policies

import com.fionn.blacky_heart.core.domain.entities.GuildVoiceJoinNotificationCondition

class GuildVoiceJoinNotificationPolicy {
    fun shouldNotify(condition: GuildVoiceJoinNotificationCondition): Boolean =
        condition.isNewJoin && condition.isVoiceChannel && condition.isSoloInChannel
}
