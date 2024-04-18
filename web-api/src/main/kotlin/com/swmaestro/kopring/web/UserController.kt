package com.swmaestro.kopring.web

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService
) {
    @PostMapping("/user")
    fun register(@RequestBody request: RegisterRequest): RegisterResponse =
        userService.register(userId = request.userId).let {
            RegisterResponse(success = true)
        }
}

data class RegisterRequest(
    val userId: String,
)

data class RegisterResponse(
    val success: Boolean,
)