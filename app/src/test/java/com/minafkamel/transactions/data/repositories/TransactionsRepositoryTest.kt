package com.minafkamel.transactions.data.repositories

import com.minafkamel.transactions.AppConstants.TRANSACTIONS_CACHE_KEY
import com.minafkamel.transactions.createTransactionsRaw
import com.minafkamel.transactions.data.Cache
import com.minafkamel.transactions.data.apis.TransactionsApi
import com.minafkamel.transactions.data.entities.TransactionsRaw
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Maybe
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TransactionsRepositoryTest {

    @Mock
    private lateinit var transactionsApi: TransactionsApi

    @Mock
    private lateinit var cache: Cache

    @Mock
    private lateinit var transactionsRepository: TransactionsRepository

    @Before
    fun setUp() {
        transactionsRepository = TransactionsRepository(transactionsApi, cache)
    }

    @Test
    fun getTransactions_CacheIsEmpty_CallsApiAndPutsInCache() {
        val fromApi = createTransactionsRaw()
        ArrangeBuilder()
            .withEmptyCache()
            .withApiResponse(fromApi)

        transactionsRepository.getTransactions().test()

        verify(cache).getItem<TransactionsRaw>(TRANSACTIONS_CACHE_KEY)
        verify(transactionsApi).fetchTransactions()
        verify(cache).put(TRANSACTIONS_CACHE_KEY, fromApi)
    }

    @Test
    fun getTransactions_CacheHaTransaction_GetsFromCache() {
        val fromCache = createTransactionsRaw(name = "from cache")
        val fromApi = createTransactionsRaw(name = "from Api")
        ArrangeBuilder()
            .withCachedTransactions(fromCache)
            .withApiResponse(fromApi)

        val transactionsObserver = transactionsRepository.getTransactions().test()

        verify(cache).getItem<TransactionsRaw?>(TRANSACTIONS_CACHE_KEY)
        transactionsObserver.assertValue(fromCache)
    }

    private inner class ArrangeBuilder {

        fun withEmptyCache() = apply {
            whenever(cache.getItem<String>(any())).thenReturn(Maybe.empty())
        }

        fun withCachedTransactions(transactionsRaw: TransactionsRaw?) = apply {
            whenever(cache.getItem<TransactionsRaw?>(TRANSACTIONS_CACHE_KEY)).thenReturn(Maybe.just(transactionsRaw))
        }

        fun withApiResponse(transactionsRaw: TransactionsRaw?) = apply {
            whenever(transactionsApi.fetchTransactions()).thenReturn(Single.just(transactionsRaw))
        }
    }
}