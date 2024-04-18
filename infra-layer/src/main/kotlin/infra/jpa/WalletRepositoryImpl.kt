package infra.jpa

import com.swmaestro.kopring.context.wallet.ChargedWalletPoint
import com.swmaestro.kopring.context.wallet.ReceivedWalletPoint
import com.swmaestro.kopring.context.wallet.RejectedWalletPoint
import com.swmaestro.kopring.context.wallet.SentWalletPoint
import com.swmaestro.kopring.context.wallet.Wallet
import com.swmaestro.kopring.context.wallet.WalletPoint
import com.swmaestro.kopring.context.wallet.WalletPoints
import com.swmaestro.kopring.context.wallet.WalletRepository
import org.springframework.stereotype.Repository

@Repository
internal class WalletRepositoryImpl(
    private val walletRepository: WalletJpaRepository,
    private val walletPointRepository: WalletPointJpaRepository
) : WalletRepository {
    override fun find(guid: String): Result<Wallet> =
        runCatching {
            walletRepository.findByGuid(guid)?.domain
                ?: error("")
        }

    override fun save(wallet: Wallet): Result<Wallet> =
        runCatching {
            WalletEntity(
                id = walletRepository.findByGuid(guid = wallet.guid)?.id,
                guid = wallet.guid,
                userId = wallet.userId,
                balance = wallet.balance,
                points = wallet.points.map {
                    WalletPointEntity(
                        id = walletPointRepository.findByGuid(guid = it.guid)?.id,
                        type = when (it) {
                            is ChargedWalletPoint -> "ChargedWalletPoint"

                            is SentWalletPoint -> "SentWalletPoint"

                            is ReceivedWalletPoint -> "ReceivedWalletPoint"

                            is RejectedWalletPoint -> "RejectedWalletPoint"
                            else -> error("")
                        },
                        guid = it.guid,
                        amount = it.amount,
                        createdAt = it.createdAt,
                        description = it.description,
                    )
                }
            ).let(walletRepository::save)
            wallet
        }

    private val WalletEntity.domain: Wallet
        get() = Wallet(
            guid = guid,
            balance = balance,
            userId = userId,
            points = WalletPoints(list = points.map {
                it.domain
            }.toMutableList())
        )

    private val WalletPointEntity.domain: WalletPoint
        get() = when (type) {
            "ChargedWalletPoint" -> ChargedWalletPoint(
                guid = guid,
                amount = amount,
                createdAt = createdAt,
            )

            "SentWalletPoint" -> SentWalletPoint(
                guid = guid,
                amount = amount,
                createdAt = createdAt,
            )

            "ReceivedWalletPoint" -> ReceivedWalletPoint(
                guid = guid,
                amount = amount,
                createdAt = createdAt,
            )

            "RejectedWalletPoint" -> RejectedWalletPoint(
                guid = guid,
                amount = amount,
                createdAt = createdAt,
            )

            else -> error("없는 유형")
        }
}