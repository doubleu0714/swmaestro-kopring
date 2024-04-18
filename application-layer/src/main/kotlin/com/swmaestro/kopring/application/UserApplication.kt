package com.swmaestro.kopring.application

import com.swmaestro.kopring.context.user.User
import com.swmaestro.kopring.context.user.UserRepository
import java.time.LocalDateTime

class UserApplication(
    private val repository: UserRepository
) {
    fun register(userId: String): Result<User> = runCatching {
        User.new(command = User.CreateCommand(userId = userId, registeredAt = LocalDateTime.now())).fold(
            onSuccess = {
                repository.save(it).getOrThrow()
            },
            onFailure = {
                throw it
            }
        )

    }
}