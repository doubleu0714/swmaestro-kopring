package com.swmaestro.kopring.infa.jpa

import org.springframework.data.jpa.repository.JpaRepository

internal interface WalletJpaRepository: JpaRepository<WalletEntity, Long> {
    fun findByGuid(guid: String): WalletEntity?
}