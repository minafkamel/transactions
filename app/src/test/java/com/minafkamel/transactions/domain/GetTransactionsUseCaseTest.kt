package com.minafkamel.transactions.domain

import com.minafkamel.transactions.createTransactionsRaw
import com.minafkamel.transactions.data.entities.TransactionsRaw
import com.minafkamel.transactions.data.repositories.TransactionsRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetTransactionsUseCaseTest{

    @Mock
    lateinit var transactionsRepository: TransactionsRepository

    private lateinit var getTransactionsUseCase: GetTransactionsUseCase

    @Before
    fun setUp() {
        getTransactionsUseCase = GetTransactionsUseCase(transactionsRepository)
    }

    @Test
    fun emitsDataCorrectly() {
        val transactionsRaw = createTransactionsRaw()
        ArrangeBuilder()
            .withRepositoryTransactions(transactionsRaw)

        val testObserver = getTransactionsUseCase.build(GetTransactionsUseCase.Params()).test()

        verify(transactionsRepository).getTransactions()
        testObserver.assertValue(transactionsRaw)
        testObserver.assertComplete()
    }

    private inner class ArrangeBuilder {

        fun withRepositoryTransactions(transactionsRaw: TransactionsRaw) = apply {
            whenever(transactionsRepository.getTransactions()).thenReturn(Single.just(transactionsRaw))
        }

    }
}