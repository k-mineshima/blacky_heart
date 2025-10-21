package com.fionn.blacky_heart.core.infrastructure.transaction

import com.fionn.blacky_heart.core.application.transaction.TransactionScope
import org.jetbrains.exposed.v1.jdbc.transactions.transaction as exposedTransaction

class TransactionScopeImpl: TransactionScope {
    override fun <T> transaction(block: () -> T): T {
        return exposedTransaction { block() }
    }
}
