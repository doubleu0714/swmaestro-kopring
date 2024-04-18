package com.swmaestro.kopring.infra.jpa

import org.springframework.data.jpa.repository.JpaRepository

internal interface UserJpaRepository: JpaRepository<UserEntity, Long> {
    fun findByGuid(guid: String): UserEntity?
}