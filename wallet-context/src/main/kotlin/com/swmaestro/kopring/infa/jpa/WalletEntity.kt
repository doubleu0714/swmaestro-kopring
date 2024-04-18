package com.swmaestro.kopring.infa.jpa

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "wallet")
internal data class WalletEntity(
    @Id
    @GeneratedValue
    val id: Long?,
    val guid: String,
    val balance: Int,
    val userId: String,
    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "walletId")
    val points: List<WalletPointEntity>
)
