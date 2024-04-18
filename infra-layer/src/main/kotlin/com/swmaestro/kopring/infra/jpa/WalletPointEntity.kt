package com.swmaestro.kopring.infra.jpa

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "wallet_point")
data class WalletPointEntity(
    @Id
    @GeneratedValue
    val id: Long?,
    val type: String,
    val guid: String,
    val amount: Int,
    val createdAt: LocalDateTime,
    val description: String,
)