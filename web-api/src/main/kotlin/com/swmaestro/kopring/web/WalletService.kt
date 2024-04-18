package com.swmaestro.kopring.web

import com.swmaestro.kopring.application.WalletApplication
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class WalletService(
    private val walletApplication: WalletApplication
) {
    @Transactional
    fun send(fromId: String, toId: String, amount: Int) {
        walletApplication.send(
            fromUserId = fromId, toUserId = toId, amount = amount
        ).getOrThrow()
    }
}