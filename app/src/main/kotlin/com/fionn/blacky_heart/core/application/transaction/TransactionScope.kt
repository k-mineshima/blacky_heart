package com.fionn.blacky_heart.core.application.transaction

interface TransactionScope {
    fun <T> transaction(block: () -> T): T
}
