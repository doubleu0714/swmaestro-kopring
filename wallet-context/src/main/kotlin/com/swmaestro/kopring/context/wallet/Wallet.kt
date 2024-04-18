package com.swmaestro.kopring.context.wallet

import java.time.LocalDateTime

class Wallet internal constructor(val balance: Int, val userId: String, val points: WalletPoints) {
    fun charge(command: ChargeCommand): Result<Wallet> = runCatching {
        Wallet(
            balance = balance + command.amount,
            userId = userId,
            points = points.apply {
                list.add(
                    ChargedWalletPoint.create(
                        command = ChargedWalletPoint.CreateCommand(
                            chargedAmount = command.amount,
                            chargedAt = LocalDateTime.now(),
                        )
                    ).getOrThrow()
                )
            }
        )
    }

    fun send(command: SendCommand): Result<Wallet> = runCatching {
        Wallet(
            balance = balance - command.amount,
            userId = userId,
            points = points.apply {
                list.add(
                    SentWalletPoint.create(
                        command = SentWalletPoint.CreateCommand(
                            sentAmount = command.amount,
                            sentAt = LocalDateTime.now(),
                        )
                    ).getOrThrow()
                )
            }
        )
    }

    fun receive(command: ReceiveCommand): Result<Wallet> = runCatching {
        Wallet(
            balance = balance + command.amount,
            userId = userId,
            points = points.apply {
                list.add(
                    ReceivedWalletPoint.create(
                        command = ReceivedWalletPoint.CreateCommand(
                            receivedAmount = command.amount,
                            receivedAt = LocalDateTime.now(),
                        )
                    ).getOrThrow()
                )
            }
        )
    }
    fun reject(command: RejectCommand): Result<Wallet> = runCatching {
        Wallet(
            balance = balance,
            userId = userId,
            points = points.apply {
                list.add(
                    RejectedWalletPoint.create(
                        command = RejectedWalletPoint.CreateCommand(
                            rejectedAmount = command.amount,
                            rejectedAt = LocalDateTime.now(),
                        )
                    ).getOrThrow()
                )
            }
        )
    }

    data class CreateCommand(val userId: String)
    data class ChargeCommand(val amount: Int)
    data class SendCommand(val amount: Int)
    data class ReceiveCommand(val amount: Int)
    data class RejectCommand(val amount: Int)

    companion object {
        fun create(command: CreateCommand): Result<Wallet> = runCatching {
            Wallet(
                balance = 0,
                userId = command.userId,
                points = WalletPoints.empty()
            )
        }
    }

}
