package com.swmaestro.kopring.context.user

import java.time.LocalDateTime
import java.util.UUID

class User internal constructor(
    val guid: String,
    val userId: String,
    val registeredAt: LocalDateTime
) {
    data class CreateCommand(
        val userId: String,
        val registeredAt: LocalDateTime,
    )
    companion object {
        fun create(command: CreateCommand): Result<User> = runCatching {
            with(command) {
                User(guid = UUID.randomUUID().toString(), userId = userId, registeredAt = registeredAt)
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (guid != other.guid) return false
        if (userId != other.userId) return false
        return registeredAt == other.registeredAt
    }

    override fun hashCode(): Int {
        var result = guid.hashCode()
        result = 31 * result + userId.hashCode()
        result = 31 * result + registeredAt.hashCode()
        return result
    }

    override fun toString(): String {
        return "User(guid='$guid', userId='$userId', registeredAt=$registeredAt)"
    }
}
