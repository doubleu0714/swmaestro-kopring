package com.swmaestro.kopring.context.wallet

class Wallet internal constructor(val balance: Int, val userId: String, val points: WalletPoints) {
    data class CreateCommand(val userId: String)
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
