package com.swmaestro.kopring.application

import com.swmaestro.kopring.context.user.User
import com.swmaestro.kopring.context.user.UserRepository
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

class UserApplicationTest {
    @Test
    fun `회원 가입을 처리한다`() {
        // given
        val userId = "test"
        val repository = FakeUserRepository()
        val sut = UserApplication(repository = repository)

        // when
        val actual: Result<User> = sut.register(userId = userId)

        // then
        actual.onSuccess {
            assertEquals(userId, it.userId)
        }.onFailure {
            assertFails {  }
        }
    }
}

class FakeUserRepository: UserRepository {
    override fun find(guid: String): Result<User> {
        TODO("Not yet implemented")
    }

    override fun save(user: User): Result<User> = runCatching {
        user
    }

}