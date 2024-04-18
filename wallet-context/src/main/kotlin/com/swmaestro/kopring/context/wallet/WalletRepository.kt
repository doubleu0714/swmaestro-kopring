package com.swmaestro.kopring.context.wallet

interface WalletRepository {
    fun find(guid: String): Result<Wallet>
    fun findByUserId(userId: String): Result<Wallet>
    fun save(wallet: Wallet): Result<Wallet>
}