package com.swmaestro.kopring.context.wallet

import java.time.LocalDateTime
import java.util.*

interface WalletPoint {
    val guid: String
    val amount: Int
    val createdAt: LocalDateTime
    val description: String
}

class ChargedWalletPoint (
    override val guid: String,
    override val amount: Int,
    override val createdAt: LocalDateTime
) : WalletPoint {
    override val description: String = "$amount 포인트 충전 $createdAt"

    data class CreateCommand(val chargedAmount: Int, val chargedAt: LocalDateTime)
    companion object {
        fun new(command: CreateCommand): Result<ChargedWalletPoint> = runCatching {
            ChargedWalletPoint(
                guid = UUID.randomUUID().toString(),
                amount = command.chargedAmount,
                createdAt = command.chargedAt
            )
        }
    }
}

class SentWalletPoint (
    override val guid: String,
    override val amount: Int,
    override val createdAt: LocalDateTime
) : WalletPoint {
    override val description: String = "$amount 포인트 전송 $createdAt"

    data class CreateCommand(val sentAmount: Int, val sentAt: LocalDateTime)
    companion object {
        fun new(command: CreateCommand): Result<SentWalletPoint> = runCatching {
            SentWalletPoint(
                guid = UUID.randomUUID().toString(),
                amount = command.sentAmount,
                createdAt = command.sentAt
            )
        }
    }
}

class ReceivedWalletPoint (
    override val guid: String,
    override val amount: Int,
    override val createdAt: LocalDateTime
) : WalletPoint {
    override val description: String = "$amount 포인트 수신 $createdAt"

    data class CreateCommand(val receivedAmount: Int, val receivedAt: LocalDateTime)
    companion object {
        fun new(command: CreateCommand): Result<ReceivedWalletPoint> = runCatching {
            ReceivedWalletPoint(
                guid = UUID.randomUUID().toString(),
                amount = command.receivedAmount,
                createdAt = command.receivedAt
            )
        }
    }
}

class RejectedWalletPoint (
    override val guid: String,
    override val amount: Int,
    override val createdAt: LocalDateTime
) : WalletPoint {
    override val description: String = "$amount 포인트 거절 $createdAt"

    data class CreateCommand(val rejectedAmount: Int, val rejectedAt: LocalDateTime)
    companion object {
        fun new(command: CreateCommand): Result<RejectedWalletPoint> = runCatching {
            RejectedWalletPoint(
                guid = UUID.randomUUID().toString(),
                amount = command.rejectedAmount,
                createdAt = command.rejectedAt
            )
        }
    }
}