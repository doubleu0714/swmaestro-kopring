package com.swmaestro.kopring.context.wallet

class WalletPoints internal constructor(
    internal val list: MutableList<WalletPoint>
) : List<WalletPoint> by list {
    companion object {
        internal fun empty() = WalletPoints(list = mutableListOf())
    }
}