package com.minafkamel.transactions.ui.main

class TransactionViewEntity(
    val image: TransactionImage,
    val title: String,
    val date: String,
    val transactionAmount: TransactionAmount,
    val category: String
) {
    class TransactionImage(val shouldShowDefaultImage: Boolean, val imageUrl: String)
    class TransactionAmount(val formattedAmount: String, val color: Int)
}