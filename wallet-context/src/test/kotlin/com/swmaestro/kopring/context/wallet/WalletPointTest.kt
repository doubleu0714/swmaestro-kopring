package com.swmaestro.kopring.context.wallet

import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import kotlin.test.assertEquals

class WalletPointTest {
    @Test
    fun `충전 포인트를 생성한다`() {
        // given
        val command = ChargedWalletPoint.CreateCommand(chargedAmount = 100, chargedAt = LocalDateTime.now())

        // when
        val actual: Result<ChargedWalletPoint> = ChargedWalletPoint.create(command = command)

        // then
        actual.onSuccess {
            assertEquals(command.chargedAmount, it.amount)
            assertEquals(command.chargedAt, it.createdAt)
            assertEquals("${command.chargedAmount} 포인트 충전 ${command.chargedAt}", it.description)
        }
    }

    @Test
    fun `전송 포인트를 생성한다`() {
        // given
        val command = SentWalletPoint.CreateCommand(sentAmount = 100, sentAt = LocalDateTime.now())

        // when
        val actual: Result<SentWalletPoint> = SentWalletPoint.create(command = command)

        // then
        actual.onSuccess {
            assertEquals(command.sentAmount, it.amount)
            assertEquals(command.sentAt, it.createdAt)
            assertEquals("${command.sentAmount} 포인트 전송 ${command.sentAt}", it.description)
        }
    }

    @Test
    fun `수신 포인트를 생성한다`() {
        // given
        val command = ReceivedWalletPoint.CreateCommand(receivedAmount = 100, receivedAt = LocalDateTime.now())

        // when
        val actual: Result<ReceivedWalletPoint> = ReceivedWalletPoint.create(command = command)

        // then
        actual.onSuccess {
            assertEquals(command.receivedAmount, it.amount)
            assertEquals(command.receivedAt, it.createdAt)
            assertEquals("${command.receivedAmount} 포인트 수신 ${command.receivedAt}", it.description)
        }
    }

    @Test
    fun `거절 포인트를 생성한다`() {
        // given
        val command = RejectedWalletPoint.CreateCommand(rejectedAmount = 100, rejectedAt = LocalDateTime.now())

        // when
        val actual: Result<RejectedWalletPoint> = RejectedWalletPoint.create(command = command)

        // then
        actual.onSuccess {
            assertEquals(command.rejectedAmount, it.amount)
            assertEquals(command.rejectedAt, it.createdAt)
            assertEquals("${command.rejectedAmount} 포인트 거절 ${command.rejectedAt}", it.description)
        }
    }
}