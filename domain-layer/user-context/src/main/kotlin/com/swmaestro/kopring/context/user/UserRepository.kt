package com.swmaestro.kopring.context.user

interface UserRepository {
    fun find(guid: String): Result<User>
    fun save(user: User): Result<User>
}