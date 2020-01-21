package com.minafkamel.transactions.data.apis

import com.minafkamel.transactions.data.entities.TransactionsRaw
import io.reactivex.Single
import retrofit2.http.GET

interface TransactionsApi {

    @GET("s/9c39669ux2jtarx/bookings.json")
    fun fetchTransactions(): Single<TransactionsRaw?>

}