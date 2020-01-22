package com.minafkamel.transactions.data.repositories

import com.minafkamel.transactions.AppConstants.TRANSACTIONS_CACHE_KEY
import com.minafkamel.transactions.data.Cache
import com.minafkamel.transactions.data.apis.TransactionsApi
import com.minafkamel.transactions.data.entities.TransactionsRaw
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransactionsRepository @Inject constructor(
    private val transactionsApi: TransactionsApi,
    private val cache: Cache
) {

    fun getTransactions(): Single<TransactionsRaw?> {
        return cache.getItem<TransactionsRaw?>(TRANSACTIONS_CACHE_KEY)
            .switchIfEmpty(fetchFromApiAndSaveToCache())
    }

    private fun fetchFromApiAndSaveToCache(): Single<TransactionsRaw?> {
        return transactionsApi.fetchTransactions()
            .doOnSuccess { saveToCache(it) }
    }

    private fun saveToCache(transactionsRaw: TransactionsRaw?): Throwable? {
        return cache.put(TRANSACTIONS_CACHE_KEY, transactionsRaw).blockingGet()
    }
}