package com.minafkamel.transactions.data.entities

import com.google.gson.annotations.SerializedName

class TransactionsRaw(@SerializedName("bookings") val transactions: List<Transaction>) {

    class Transaction(
        val category: String,
        @SerializedName("category_icon") val categoryIcon: String,
        val name: String,
        val timesStamp: String,
        val amount: Amount
    ) {
        class Amount(val currency: String, val value: Double)
    }
}