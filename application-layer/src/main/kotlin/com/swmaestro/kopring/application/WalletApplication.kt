package com.swmaestro.kopring.application

class WalletApplication(
    private val repository: WalletRepository
) {
    fun send(fromUserId: String, toUserId: String, amount: Int): Result<Wallet> = runCatching {
        val to = repository.findByUserId(toUserId).getOrThrow()
        val from = repository.findByUserId(fromUserId).getOrThrow()
        to.receive(command = Wallet.ReceiveCommand(amount = amount)).getOrThrow().let(repository::save).getOrThrow()
        return from.send(command = Wallet.SendCommand(amount = amount)).getOrThrow().let(repository::save)
    }
}