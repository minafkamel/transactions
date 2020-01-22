package com.minafkamel.transactions

import com.minafkamel.transactions.data.entities.TransactionsRaw

fun createTransactionsRaw(
    category: String? = "category",
    categoryIcon: String? = "categoryIcon",
    name: String? = "name",
    timesStamp: String? = "2020-01-09T14:46:33Z",
    currency: String? = "currency",
    value: Double? = 1.0
): TransactionsRaw {
    return TransactionsRaw(
        listOf(
            TransactionsRaw.Transaction(
                category,
                categoryIcon,
                name,
                timesStamp,
                TransactionsRaw.Transaction.Amount(currency, value)
            )
        )
    )
}
