package com.swmaestro.kopring.context.wallet

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertTrue

class WalletTest {
    @Test
    fun `지갑을 생성한다`() {
        // given

        // when        val actual: Result<Wallet> = Wallet.new(command = command)

        // then
    }

    @Test
    fun `충전을 하면 지갑의 잔액이 증가하고 충전 포인트가 추가된다`() {
        // given

        // when

        // then
    }

    @Test
    fun `전송하면 지갑의 잔액이 줄고 전송 포인트가 추가된다`() {
        // given

        // when

        // then
    }

    @Test
    fun `수신하면 지갑의 잔액이 증가하고 수신 포인트가 추가된다`() {
        // given

        // when

        // then
    }

    @Test
    fun `거절하면 지갑의 잔액은 변경되지 않고 거절 포인트 추가된다`() {
        // given

        // when

        // then
    }

    @Test
    fun `지갑의 잔액이 전송금액보다 작으면 전송 할 수 없다`() {
        // given

        // when

        // then
    }
}