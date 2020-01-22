package com.minafkamel.transactions.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.minafkamel.transactions.RxSchedulersOverrideRule
import com.minafkamel.transactions.createTransactionsRaw
import com.minafkamel.transactions.createTransactionsViewEntity
import com.minafkamel.transactions.data.entities.TransactionsRaw
import com.minafkamel.transactions.domain.GetTransactionsUseCase
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @Mock
    private lateinit var mapper: TransactionsMapper

    @Mock
    private lateinit var getTransactionsUseCase: GetTransactionsUseCase

    private lateinit var mainViewModel: MainViewModel

    @get:Rule
    val overrideSchedulersRule = RxSchedulersOverrideRule

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        mainViewModel = MainViewModel(getTransactionsUseCase, mapper)
    }

    @Test
    fun onBind_CallsGetTransactionsUseCaseAndMapsAndShowsTransactions() {
        val transactionsRaw = createTransactionsRaw()
        val transactionsViewEntity = createTransactionsViewEntity()
        ArrangeBuilder()
            .withTransactionsRaw(transactionsRaw)
            .withTransactionsViewEntity(transactionsViewEntity)

        mainViewModel.onBind(CompositeDisposable())

        verify(getTransactionsUseCase).build(GetTransactionsUseCase.Params())
        verify(mapper).toTransactionsViewEntities(transactionsRaw)
        assertEquals(transactionsViewEntity, mainViewModel.transactionViewEntitiesLiveData.value)
    }

    private inner class ArrangeBuilder {

        fun withTransactionsRaw(transactionsRaw: TransactionsRaw) = apply {
            whenever(getTransactionsUseCase.build(any())).thenReturn(Single.just(transactionsRaw))
        }

        fun withTransactionsViewEntity(viewEntities: List<TransactionViewEntity>) = apply {
            whenever(mapper.toTransactionsViewEntities(any())).thenReturn(viewEntities)
        }
    }

}