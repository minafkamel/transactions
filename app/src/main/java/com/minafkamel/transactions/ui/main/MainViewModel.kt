package com.minafkamel.transactions.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.minafkamel.transactions.domain.GetTransactionsUseCase
import com.minafkamel.transactions.ui.base.BaseViewModel
import com.vikingsen.inject.viewmodel.ViewModelInject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel @ViewModelInject constructor(
    private val getTransactionsUseCase: GetTransactionsUseCase,
    private val mapper: TransactionsMapper
) : BaseViewModel() {

    val transactionViewEntitiesLiveData = MutableLiveData<List<TransactionViewEntity>>()

    override fun onBind(d: CompositeDisposable) {

        d.add(getTransactionsUseCase.build(GetTransactionsUseCase.Params())
            .map { mapper.toTransactionsViewEntities(it) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ showTransactions(it) }, { handleError(it) })
        )
    }

    private fun handleError(it: Throwable) {
        Log.d(TAG, it.toString())
    }

    private fun showTransactions(transactionsViewEntities: List<TransactionViewEntity>) {
        transactionViewEntitiesLiveData.postValue(transactionsViewEntities)
    }

    companion object {
        const val TAG = "MainViewModel: "
    }

}