package com.swmaestro.kopring.context.wallet

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertTrue

class WalletTest {
    @Test
    fun `지갑을 생성한다`() {
        // given
        val command = Wallet.CreateCommand(userId = "userId")

        // when
        val actual: Result<Wallet> = Wallet.new(command = command)

        // then
        actual.onSuccess {
            assertEquals(0, it.balance)
            assertEquals(command.userId, it.userId)
            assertEquals(0, it.points.size)
        }
    }

    @Test
    fun `충전을 하면 지갑의 잔액이 증가하고 충전 포인트가 추가된다`() {
        // given
        val sut: Wallet = Wallet.new(command = Wallet.CreateCommand(userId = "userId")).getOrThrow()
        val command = Wallet.ChargeCommand(amount = 100)

        // when
        val actual: Result<Wallet> = sut.charge(command = command)

        // then
        actual.onSuccess {
            assertEquals(command.amount, it.balance)
            assertTrue { it.points[0] is ChargedWalletPoint }
        }
    }

    @Test
    fun `전송하면 지갑의 잔액이 줄고 전송 포인트가 추가된다`() {
        // given
        val sut: Wallet = Wallet.new(command = Wallet.CreateCommand(userId = "userId")).getOrThrow()
        sut.charge(command = Wallet.ChargeCommand(amount = 100))
        val command = Wallet.SendCommand(amount = 50)

        // when
        val actual: Result<Wallet> = sut.send(command = command)

        // then
        actual.onSuccess {
            assertEquals(sut.balance - command.amount, it.balance)
            assertTrue { it.points[0] is ChargedWalletPoint }
            assertTrue { it.points[1] is SentWalletPoint }
        }
    }

    @Test
    fun `수신하면 지갑의 잔액이 증가하고 수신 포인트가 추가된다`() {
        // given
        val sut: Wallet = Wallet.new(command = Wallet.CreateCommand(userId = "userId")).getOrThrow()
        val command = Wallet.ReceiveCommand(amount = 100)

        // when
        val actual: Result<Wallet> = sut.receive(command = command)

        // then
        actual.onSuccess {
            assertEquals(command.amount, it.balance)
            assertTrue { it.points[0] is ReceivedWalletPoint }
        }
    }

    @Test
    fun `거절하면 지갑의 잔액은 변경되지 않고 거절 포인트 추가된다`() {
        // given
        val sut: Wallet = Wallet.new(command = Wallet.CreateCommand(userId = "userId")).getOrThrow()
        val command = Wallet.RejectCommand(amount = 100)

        // when
        val actual: Result<Wallet> = sut.reject(command = command)

        // then
        actual.onSuccess {
            assertEquals(sut.balance, it.balance)
            assertTrue { it.points[0] is RejectedWalletPoint }
        }
    }

    @Test
    fun `지갑의 잔액이 전송금액보다 작으면 전송 할 수 없다`() {
        // given
        val sut: Wallet = Wallet.new(command = Wallet.CreateCommand(userId = "userId")).getOrThrow()
        sut.charge(command = Wallet.ChargeCommand(amount = 100))
        val command = Wallet.SendCommand(amount = 2000)

        // when
        val actual: Result<Wallet> = sut.send(command = command)

        // then
        actual.onSuccess {
            assertFails { "실패" }
        }
    }
}