package infra.jpa

import org.springframework.data.jpa.repository.JpaRepository

internal interface WalletPointJpaRepository: JpaRepository<WalletPointEntity, Long> {
    fun findByGuid(guid: String): WalletPointEntity?
}