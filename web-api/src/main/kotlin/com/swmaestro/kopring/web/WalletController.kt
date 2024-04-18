package com.swmaestro.kopring.web

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class WalletController(
    private val walletService: WalletService
) {
    @PostMapping("/wallet/send")
    fun send(@RequestBody request: SendRequest): SendResponse =
        walletService.send(fromId = request.fromUserId, toId = request.toUserId, amount = request.amount).let {
            SendResponse(success = true)
        }
}

data class SendRequest(
    val fromUserId: String,
    val toUserId: String,
    val amount: Int
)

data class SendResponse(
    val success: Boolean,
)