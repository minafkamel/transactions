package com.minafkamel.transactions.ui.main

import android.annotation.SuppressLint
import com.minafkamel.transactions.R
import com.minafkamel.transactions.data.entities.TransactionsRaw
import com.minafkamel.transactions.ui.main.TransactionViewEntity.TransactionImage
import java.text.SimpleDateFormat
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransactionsMapper @Inject constructor() {

    fun toTransactionsViewEntities(transactionsRaw: TransactionsRaw?): List<TransactionViewEntity> {
        val transactions = requireNotNull(transactionsRaw, { TAG + "transactionsRaw is null" })
        val transactionsList =
            requireNotNull(transactions.transactions, { TAG + "transactions is null" })

        return transactionsList.map {
            val name = requireNotNull(it.name, { TAG + "name is null" })
            val timestamp = requireNotNull(it.timestamp, { TAG + "timestamp is null" })
            val amount = requireNotNull(it.amount, { TAG + "amount is null" })
            val value = requireNotNull(amount.value, { TAG + "value is null" })
            val currency = requireNotNull(amount.currency, { TAG + "currency is null" })
            val category = requireNotNull(it.category, { TAG + "category is null" })

            TransactionViewEntity(
                createImage(it.categoryIcon),
                name,
                formatDate(timestamp),
                createTransactionAmount(value, currency),
                category
            )
        }
    }

    private fun createImage(categoryIcon: String?): TransactionImage {
        return if (categoryIcon.isNullOrEmpty()) {
            TransactionImage(true, "")
        } else {
            TransactionImage(false, categoryIcon)
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun formatDate(timestamp: String): String {
        return SimpleDateFormat(DATE_PATTERN).format(SimpleDateFormat(API_FORMAT).parse(timestamp))
    }

    private fun createTransactionAmount(
        value: Double,
        currency: String
    ): TransactionViewEntity.TransactionAmount {

        val amount = formatAmount(value, currency)
        val color = determineTextColor(value)
        return TransactionViewEntity.TransactionAmount(amount, color)
    }

    private fun formatAmount(value: Double, currency: String): String {
        val factoredAmount = value * AMOUNT_CONVERSION_FACTOR
        val prefixedAmount = addPrefixIfAmountIsPositive(factoredAmount)
        return "$prefixedAmount $currency"
    }

    private fun addPrefixIfAmountIsPositive(factoredAmount: Double): String {
        return if (factoredAmount > 0) POSITIVE_PREFIX + factoredAmount else factoredAmount.toString()
    }

    private fun determineTextColor(value: Double): Int {
        return if (value > 0) R.color.colorPrimary else R.color.red
    }

    companion object {
        const val TAG = "TransactionsMapper: "
        const val AMOUNT_CONVERSION_FACTOR = 0.01
        const val DATE_PATTERN = "dd/MM/yyyy"
        const val API_FORMAT = "yyyy-MM-dd HH:mm:ss"
        const val POSITIVE_PREFIX = "+"
    }
}