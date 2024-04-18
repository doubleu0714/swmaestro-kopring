package com.swmaestro.kopring.context.user

import java.time.LocalDateTime

class User internal constructor(
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
                User(userId = userId, registeredAt = registeredAt)
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (userId != other.userId) return false
        return registeredAt == other.registeredAt
    }

    override fun hashCode(): Int {
        var result = userId.hashCode()
        result = 31 * result + registeredAt.hashCode()
        return result
    }

    override fun toString(): String {
        return "User(userId='$userId', registeredAt=$registeredAt)"
    }
}
