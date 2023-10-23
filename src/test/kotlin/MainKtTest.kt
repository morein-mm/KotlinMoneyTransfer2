import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    @Test
    fun calcCommission_VisaMinCommission() {
        val cardType = "Visa"
        val thisMonthTransferAmount = 10_000.0 //сумма переводов за месяц (без комиссии), не включая текущий перевод
        val transferAmount = 1_000.00 //сумма перевода

        val result = calcCommission(
            cardType = cardType,
            thisMonthTransferAmount = thisMonthTransferAmount,
            transferAmount = transferAmount
        )

        assertEquals(35.0, result, 0.0)
    }

    @Test
    fun calcCommission_MirMinCommission() {
        val cardType = "Мир"
        val thisMonthTransferAmount = 10_000.0 //сумма переводов за месяц (без комиссии), не включая текущий перевод
        val transferAmount = 1_000.00 //сумма перевода

        val result = calcCommission(
            cardType = cardType,
            thisMonthTransferAmount = thisMonthTransferAmount,
            transferAmount = transferAmount
        )

        assertEquals(35.0, result, 0.0)
    }

    @Test
    fun calcCommission_VisaPercentCommission() {
        val cardType = "Visa"
        val thisMonthTransferAmount = 10_000.0 //сумма переводов за месяц (без комиссии), не включая текущий перевод
        val transferAmount = 15_000.00 //сумма перевода

        val result = calcCommission(
            cardType = cardType,
            thisMonthTransferAmount = thisMonthTransferAmount,
            transferAmount = transferAmount
        )

        assertEquals(112.5, result, 0.0)
    }

    @Test
    fun calcCommission_MirPercentCommission() {
        val cardType = "Мир"
        val thisMonthTransferAmount = 10_000.0 //сумма переводов за месяц (без комиссии), не включая текущий перевод
        val transferAmount = 16_000.00 //сумма перевода

        val result = calcCommission(
            cardType = cardType,
            thisMonthTransferAmount = thisMonthTransferAmount,
            transferAmount = transferAmount
        )

        assertEquals(120.0, result, 0.0)
    }

    @Test
    fun calcCommission_MaestroNoCommission() {
        val cardType = "Maestro"
        val thisMonthTransferAmount = 10_000.0 //сумма переводов за месяц (без комиссии), не включая текущий перевод
        val transferAmount = 1_000.00 //сумма перевода

        val result = calcCommission(
            cardType = cardType,
            thisMonthTransferAmount = thisMonthTransferAmount,
            transferAmount = transferAmount
        )

        assertEquals(0.0, result, 0.0)
    }

    @Test
    fun calcCommission_MastercardNoCommission() {
        val cardType = "Mastercard"
        val thisMonthTransferAmount = 10_000.0 //сумма переводов за месяц (без комиссии), не включая текущий перевод
        val transferAmount = 1_000.00 //сумма перевода

        val result = calcCommission(
            cardType = cardType,
            thisMonthTransferAmount = thisMonthTransferAmount,
            transferAmount = transferAmount
        )

        assertEquals(0.0, result, 0.0)
    }

    @Test
    fun calcCommission_MaestroCommission() {
        val cardType = "Maestro"
        val thisMonthTransferAmount = 80_000.0 //сумма переводов за месяц (без комиссии), не включая текущий перевод
        val transferAmount = 1_000.00 //сумма перевода

        val result = calcCommission(
            cardType = cardType,
            thisMonthTransferAmount = thisMonthTransferAmount,
            transferAmount = transferAmount
        )

        assertEquals(27.5, result, 0.0)
    }

    @Test
    fun calcCommission_MastercardCommission() {
        val cardType = "Mastercard"
        val thisMonthTransferAmount = 80_000.0 //сумма переводов за месяц (без комиссии), не включая текущий перевод
        val transferAmount = 1_000.00 //сумма перевода

        val result = calcCommission(
            cardType = cardType,
            thisMonthTransferAmount = thisMonthTransferAmount,
            transferAmount = transferAmount
        )

        assertEquals(27.5, result, 0.0)
    }

    @Test
    fun calcCommission_DefaultValue() {
        val transferAmount = 1_000.00 //сумма перевода

        val result = calcCommission(
            transferAmount = transferAmount,
        )

        assertEquals(0.0, result, 0.0)
    }

    @Test
    fun calcCommission_DefaultCardType() {
        val thisMonthTransferAmount = 80_000.0 //сумма переводов за месяц (без комиссии), не включая текущий перевод
        val transferAmount = 1_000.00 //сумма перевода

        val result = calcCommission(
            transferAmount = transferAmount,
            thisMonthTransferAmount = thisMonthTransferAmount
        )

        assertEquals(0.0, result, 0.0)
    }

    @Test
    fun calcCommission_DefaultThisMonthTransferAmount() {
        val cardType = "Mastercard"
        val transferAmount = 1_000.00 //сумма перевода

        val result = calcCommission(
            cardType = cardType,
            transferAmount = transferAmount,
        )

        assertEquals(0.0, result, 0.0)
    }

    @Test
    fun calcCommission_VKPay() {
        val cardType = "VKPay"
        val thisMonthTransferAmount = 10_000.0 //сумма переводов за месяц (без комиссии), не включая текущий перевод
        val transferAmount = 15_000.00 //сумма перевода

        val result = calcCommission(
            cardType = cardType,
            thisMonthTransferAmount = thisMonthTransferAmount,
            transferAmount = transferAmount
        )

        assertEquals(0.0, result, 0.0)
    }

    @Test
    fun checkLimits_VKPayLimitExeeding() {
        val cardType = "VK Pay"
        val thisMonthTransferAmount = 10_000.0 //сумма переводов за месяц (без комиссии), не включая текущий перевод
        val transferAmount = 20_000.00 //сумма перевода
        val thisDayTransferAmount = 0.0 //сумма переводов за текуший день (без комиссии), не включая текущий перевод

        val result = checkLimits(
            cardType = cardType,
            thisMonthTransferAmount = thisMonthTransferAmount,
            thisDayTransferAmount = thisDayTransferAmount,
            transferAmount = transferAmount
        )

        assertEquals(false, result)
    }
    @Test
    fun checkLimits_VKPayLimitOK() {
        val cardType = "VK Pay"
        val thisMonthTransferAmount = 10_000.0 //сумма переводов за месяц (без комиссии), не включая текущий перевод
        val transferAmount = 5_000.00 //сумма перевода
        val thisDayTransferAmount = 0.0 //сумма переводов за текуший день (без комиссии), не включая текущий перевод

        val result = checkLimits(
            cardType = cardType,
            thisMonthTransferAmount = thisMonthTransferAmount,
            thisDayTransferAmount = thisDayTransferAmount,
            transferAmount = transferAmount
        )

        assertEquals(true, result)
    }

    @Test
    fun checkLimits_NotVKPayLimitExeedingLimitOK() {
        val cardType = "Maestro"
        val thisMonthTransferAmount = 11_000.0 //сумма переводов за месяц (без комиссии), не включая текущий перевод
        val transferAmount = 5_000.00 //сумма перевода
        val thisDayTransferAmount = 11_000.0 //сумма переводов за текуший день (без комиссии), не включая текущий перевод

        val result = checkLimits(
            cardType = cardType,
            thisMonthTransferAmount = thisMonthTransferAmount,
            thisDayTransferAmount = thisDayTransferAmount,
            transferAmount = transferAmount
        )

        assertEquals(false, result)
    }
    @Test
    fun checkLimits_NotVKPayLimitOK() {
        val cardType = "Maestro"
        val thisMonthTransferAmount = 11_000.0 //сумма переводов за месяц (без комиссии), не включая текущий перевод
        val transferAmount = 5_000.00 //сумма перевода
        val thisDayTransferAmount = 10_000.0 //сумма переводов за текуший день (без комиссии), не включая текущий перевод

        val result = checkLimits(
            cardType = cardType,
            thisMonthTransferAmount = thisMonthTransferAmount,
            thisDayTransferAmount = thisDayTransferAmount,
            transferAmount = transferAmount
        )

        assertEquals(true, result)
    }


}