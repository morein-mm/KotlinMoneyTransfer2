fun main() {

    val cardType = "Mastercard"
    val thisMonthTransferAmount = 10_000.0 //сумма переводов за месяц (без комиссии), не включая текущий перевод
    val transferAmount = 5_000.00 //сумма перевода
    val thisDayTransferAmount = 0.0 //сумма переводов за текуший день (без комиссии), не включая текущий перевод

    checkTransferAndCalcCommission(cardType, thisMonthTransferAmount, thisDayTransferAmount, transferAmount)

}

fun checkTransferAndCalcCommission(cardType: String, thisMonthTransferAmount: Double, thisDayTransferAmount:Double, transferAmount: Double) {
    if (checkLimits(cardType, thisMonthTransferAmount, thisDayTransferAmount, transferAmount))
        println(calcCommission(cardType, thisMonthTransferAmount, transferAmount))
    else
        println("Перевод невозможен из-за превышения лимитов")
}

fun checkLimits(cardType: String, thisMonthTransferAmount: Double, thisDayTransferAmount:Double, transferAmount: Double):Boolean {
    val cardMonthLimit = 600_000
    val cardDayLimit = 15_000
    val cardVKPayMonthLimit = 40_000
    val cardVKPayOnceLimit = 15_000

    return when (cardType) {
        "VK Pay" -> ((thisMonthTransferAmount + transferAmount) <= cardVKPayMonthLimit) && (transferAmount <= cardVKPayOnceLimit)
        else -> ((thisMonthTransferAmount + transferAmount) <= cardMonthLimit) && (thisDayTransferAmount + transferAmount <= cardDayLimit)
    }
}

fun calcCommission(cardType: String = "VK Pay", thisMonthTransferAmount: Double = 0.0, transferAmount: Double): Double {
    return when (cardType) {
        "Mastercard", "Maestro" -> {
            val thisMonthTransferLimit = 75_000
            val commissionPercent = 0.75
            val additionalCommissionAmount = 20
            return if ((thisMonthTransferAmount + transferAmount) > thisMonthTransferLimit) commissionPercent * transferAmount / 100 + additionalCommissionAmount else 0.0
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