package com.minafkamel.transactions

import com.minafkamel.transactions.data.entities.TransactionsRaw
import com.minafkamel.transactions.ui.main.TransactionViewEntity

fun createTransactionsRaw(
    category: String? = "category",
    categoryIcon: String? = "categoryIcon",
    name: String? = "name",
    timesStamp: String? = "2020-01-09 14:46:33",
    amount: TransactionsRaw.Transaction.Amount? = createAmount()
): TransactionsRaw {
    return TransactionsRaw(
        listOf(
            TransactionsRaw.Transaction(
                category,
                categoryIcon,
                name,
                timesStamp,
                amount
            )
        )
    )
}

fun createAmount(
    value: Double? = 1.0,
    currency: String? = "currency"
): TransactionsRaw.Transaction.Amount {
    return TransactionsRaw.Transaction.Amount(currency, value)
}

fun createTransactionsViewEntity() = listOf(
    TransactionViewEntity(
        TransactionViewEntity.TransactionImage(true, ""),
        "",
        "",
        TransactionViewEntity.TransactionAmount("", 1)
        , ""
    )
)
