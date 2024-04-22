package com.swmaestro.kopring.context.wallet

import java.time.LocalDateTime
import java.util.UUID

class Wallet (
    val guid: String,
    val balance: Int,
    val userId: String,
    val points: WalletPoints
) {
    fun charge(command: ChargeCommand): Result<Wallet> = runCatching {
        applyWallet(
            balance = balance + command.amount,
            point = ChargedWalletPoint.new(
                command = ChargedWalletPoint.CreateCommand(
                    chargedAmount = command.amount,
                    chargedAt = LocalDateTime.now(),
                )
            ).getOrThrow()
        )
    }

    fun send(command: SendCommand): Result<Wallet> = runCatching {
        require(balance >= command.amount)
        applyWallet(
            balance = balance - command.amount,
            point = SentWalletPoint.new(
                command = SentWalletPoint.CreateCommand(
                    sentAmount = command.amount,
                    sentAt = LocalDateTime.now(),
                )
            ).getOrThrow()
        )
    }

    fun receive(command: ReceiveCommand): Result<Wallet> = runCatching {
        applyWallet(
            balance = balance + command.amount,
            point = ReceivedWalletPoint.new(
                command = ReceivedWalletPoint.CreateCommand(
                    receivedAmount = command.amount,
                    receivedAt = LocalDateTime.now(),
                )
            ).getOrThrow()
        )
    }

    fun reject(command: RejectCommand): Result<Wallet> = runCatching {
        applyWallet(
            balance = balance,
            point = RejectedWalletPoint.new(
                command = RejectedWalletPoint.CreateCommand(
                    rejectedAmount = command.amount,
                    rejectedAt = LocalDateTime.now(),
                )
            ).getOrThrow()
        )
    }

    private fun applyWallet(balance: Int, point: WalletPoint) = Wallet(
        guid = guid,
        balance = balance,
        userId = userId,
        points = points.apply {
            list.add(
                point
            )
        }
    )

    data class CreateCommand(val userId: String)
    data class ChargeCommand(val amount: Int)
    data class SendCommand(val amount: Int)
    data class ReceiveCommand(val amount: Int)
    data class RejectCommand(val amount: Int)

    companion object {
        fun new(command: CreateCommand): Result<Wallet> = runCatching {
            Wallet(
                guid = UUID.randomUUID().toString(),
                balance = 0,
                userId = command.userId,
                points = WalletPoints.empty()
            )
        }
    }
}
