package com.swmaestro.kopring.application

import com.swmaestro.kopring.context.wallet.ChargedWalletPoint
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import kotlin.test.assertEquals

class WalletApplicationTest {
    @Test
    fun `다른 회원에게 포인트를 전송한다`() {
        // given
        val fromUserId = "fromUserId"
        val toUserId = "toUserId"
        val repository = FakeWalletRepository()
        val sut = WalletApplication(repository = repository)

        // when
        val actual: Result<Wallet> = sut.send(fromUserId = fromUserId, toUserId = toUserId, amount = 5000)

        // then
        actual.onSuccess {
            assertEquals(5000, it.balance)
        }
    }
}

class FakeWalletRepository : WalletRepository {
    override fun find(guid: String): Result<Wallet> = runCatching {
        Wallet(
            guid = guid,
            balance = 10000,
            userId = "userId",
            points = WalletPoints(
                list = mutableListOf(
                    ChargedWalletPoint.new(
                        command = ChargedWalletPoint.CreateCommand(
                            chargedAt = LocalDateTime.now(),
                            chargedAmount = 10000,
                        )
                    ).getOrThrow()
                )
            )
        )
    }

    override fun findByUserId(userId: String): Result<Wallet> = runCatching {
        if (userId == "fromUserId") {
            Wallet(
                guid = "guid1",
                balance = 10000,
                userId = "fromUserId",
                points = WalletPoints(
                    list = mutableListOf(
                        ChargedWalletPoint.new(
                            command = ChargedWalletPoint.CreateCommand(
                                chargedAt = LocalDateTime.now(),
                                chargedAmount = 10000,
                            )
                        ).getOrThrow()
                    )
                )
            )
        } else if (userId == "toUserId") {
            Wallet(
                guid = "guid1",
                balance = 0,
                userId = "toUserId",
                points = WalletPoints(
                    list = mutableListOf(
                        ChargedWalletPoint.new(
                            command = ChargedWalletPoint.CreateCommand(
                                chargedAt = LocalDateTime.now(),
                                chargedAmount = 10000,
                            )
                        ).getOrThrow()
                    )
                )
            )
        } else {
            error("")
        }
    }

    override fun save(wallet: Wallet): Result<Wallet> = runCatching {
        wallet
    }

}