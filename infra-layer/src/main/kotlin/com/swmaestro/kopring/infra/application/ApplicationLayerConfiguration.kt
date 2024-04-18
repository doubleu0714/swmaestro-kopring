package com.swmaestro.kopring.infra.application

import com.swmaestro.kopring.application.UserApplication
import com.swmaestro.kopring.application.WalletApplication
import com.swmaestro.kopring.context.user.UserRepository
import com.swmaestro.kopring.context.wallet.WalletRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApplicationLayerConfiguration(
    private val userRepository: UserRepository,
    private val walletRepository: WalletRepository
) {
    @Bean
    fun userApplication(): UserApplication =
        UserApplication(repository = userRepository, walletRepository = walletRepository)

    @Bean
    fun walletApplication(): WalletApplication =
        WalletApplication(repository = walletRepository)
}