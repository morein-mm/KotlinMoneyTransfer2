fun main() {
    println(calcCommission("Visa", 74_000.0, 785664.00 ))
}

fun calcCommission(cardType: String = "VK Pay", thisMonthTransferAmount: Double = 0.0, transferAmount: Double): Double {
    return when (cardType) {
        "Mastercard", "Maestro" -> {
            val thisMonthTransferLimit = 75_000
            val commissionPercent = 0.75
            val additionalCommissionAmount = 20
            return if (thisMonthTransferAmount > thisMonthTransferLimit) commissionPercent * transferAmount / 100 + additionalCommissionAmount else 0.0
        }
        "Visa", "Мир" -> {
            val commissionPercent = 0.75
            val minCommissionAmount = 35.0
            val commission = commissionPercent * transferAmount / 100
            return if (commission < minCommissionAmount) minCommissionAmount else commission
        }
        else -> 0.0
    }
}