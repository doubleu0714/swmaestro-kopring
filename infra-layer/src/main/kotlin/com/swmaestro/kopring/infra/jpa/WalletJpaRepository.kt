package com.swmaestro.kopring.infra.jpa

import org.springframework.data.jpa.repository.JpaRepository

internal interface WalletJpaRepository: JpaRepository<WalletEntity, Long> {
    fun findByGuid(guid: String): WalletEntity?
    fun findByUserId(userId: String): WalletEntity?
}