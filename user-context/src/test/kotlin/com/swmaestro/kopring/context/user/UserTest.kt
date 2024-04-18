package com.swmaestro.kopring.context.user

import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import kotlin.test.assertEquals

class UserTest {
    @Test
    fun `회원을 생성하면 아이디와 가입일시가 설정된다`() {
        // given

        // when
        val userId = "userId"
        val registeredAt = LocalDateTime.now()
        val command = User.CreateCommand(userId = userId, registeredAt = registeredAt)
        val actual: Result<User> = User.create(command = command)

        // then
        actual.getOrThrow()
        actual.onSuccess {
            assertEquals(userId, it.userId)
            assertEquals(registeredAt, it.registeredAt)
        }
    }
}