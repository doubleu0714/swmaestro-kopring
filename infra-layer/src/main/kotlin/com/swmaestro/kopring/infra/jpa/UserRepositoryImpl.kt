package com.swmaestro.kopring.infra.jpa

import com.swmaestro.kopring.context.user.User
import com.swmaestro.kopring.context.user.UserRepository
import org.springframework.stereotype.Repository

@Repository
internal class UserRepositoryImpl(
    private val repository: UserJpaRepository
) : UserRepository {
    override fun find(guid: String): Result<User> = runCatching {
        repository.findByGuid(guid = guid)?.domain ?: error("저장된 값이 없습니다.")
    }

    override fun save(user: User): Result<User> = runCatching {
        UserEntity(
            id = repository.findByGuid(guid = user.guid)?.id,
            guid = user.guid,
            userId = user.userId,
            registeredAt = user.registeredAt
        ).let(repository::save).domain
    }

    private val UserEntity.domain: User
        get() = User(
            guid = guid,
            userId = userId,
            registeredAt = registeredAt
        )
}