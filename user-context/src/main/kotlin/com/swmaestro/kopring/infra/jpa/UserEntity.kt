package com.swmaestro.kopring.infra.jpa

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "user")
internal data class UserEntity(
    @Id
    @GeneratedValue
    val id: Long?,
    val guid: String,
    val userId: String,
    val registeredAt: LocalDateTime,
)