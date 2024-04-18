package com.swmaestro.kopring.context.wallet

import java.time.LocalDateTime

interface WalletPoint {
    val amount: Int
    val createdAt: LocalDateTime
    val description: String
}

class ChargedWalletPoint internal constructor(
    override val amount: Int,
    override val createdAt: LocalDateTime
) : WalletPoint {
    override val description: String = "$amount 포인트 충전 $createdAt"

    data class CreateCommand(val chargedAmount: Int, val chargedAt: LocalDateTime)
    companion object {
        fun create(command: CreateCommand): Result<ChargedWalletPoint> = runCatching {
            ChargedWalletPoint(amount = command.chargedAmount, createdAt = command.chargedAt)
        }
    }
}

class SentWalletPoint internal constructor(override val amount: Int, override val createdAt: LocalDateTime) : WalletPoint{
    override val description: String = "$amount 포인트 전송 $createdAt"

    data class CreateCommand(val sentAmount: Int, val sentAt: LocalDateTime)
    companion object {
        fun create(command: CreateCommand): Result<SentWalletPoint> = runCatching {
            SentWalletPoint(amount = command.sentAmount, createdAt = command.sentAt)
        }
    }
}

class ReceivedWalletPoint internal constructor(override val amount: Int, override val createdAt: LocalDateTime) : WalletPoint{
    override val description: String = "$amount 포인트 수신 $createdAt"

    data class CreateCommand(val receivedAmount: Int, val receivedAt: LocalDateTime)
    companion object {
        fun create(command: CreateCommand): Result<ReceivedWalletPoint> = runCatching {
            ReceivedWalletPoint(amount = command.receivedAmount, createdAt = command.receivedAt)
        }
    }
}

class RejectedWalletPoint internal constructor(override val amount: Int, override val createdAt: LocalDateTime) : WalletPoint{
    override val description: String = "$amount 포인트 거절 $createdAt"

    data class CreateCommand(val rejectedAmount: Int, val rejectedAt: LocalDateTime)
    companion object {
        fun create(command: CreateCommand): Result<RejectedWalletPoint> = runCatching {
            RejectedWalletPoint(amount = command.rejectedAmount, createdAt = command.rejectedAt)
        }
    }
}