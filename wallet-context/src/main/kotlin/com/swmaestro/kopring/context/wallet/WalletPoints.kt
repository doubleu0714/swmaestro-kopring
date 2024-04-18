package com.swmaestro.kopring.context.wallet

class WalletPoints (
    internal val list: MutableList<WalletPoint>
) : List<WalletPoint> by list {
    companion object {
        internal fun empty() = WalletPoints(list = mutableListOf())
    }
}