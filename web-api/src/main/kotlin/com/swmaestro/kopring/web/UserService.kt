package com.swmaestro.kopring.web

import com.swmaestro.kopring.application.UserApplication
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userApplication: UserApplication
) {
    @Transactional
    fun register(userId: String) {
        userApplication.register(userId = userId).getOrElse {
            // TODO: 에러 로깅
        }
    }
}