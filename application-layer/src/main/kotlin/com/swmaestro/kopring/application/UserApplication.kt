package com.swmaestro.kopring.application

import java.time.LocalDateTime

class UserApplication(
    private val repository: UserRepository,
    private val walletRepository: WalletRepository,
) {
    fun register(userId: String): Result<User> = runCatching {
        Wallet.new(command = Wallet.CreateCommand(
            userId = userId
        )).fold(
            onSuccess = {
                walletRepository.save(it).getOrThrow()
            },
            onFailure = {
                throw it
            }
        )
        User.new(command = User.CreateCommand(userId = userId, registeredAt = LocalDateTime.now())).fold(
            onSuccess = {
                repository.save(it).getOrThrow()
            },
            onFailure = {
                throw it
            }
        )
    }
}