package com.minafkamel.transactions.ui.main

import android.os.Bundle
import androidx.lifecycle.Observer
import com.minafkamel.transactions.R
import com.minafkamel.transactions.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : BaseActivity<MainViewModel>() {

    override fun observeLiveData() {
        viewModel.transactionViewEntitiesLiveData.observe(this, Observer { showTransactions(it) })
    }

    private fun showTransactions(transactions: List<TransactionViewEntity>) {
        transactionsRecyclerView.adapter = TransactionsAdapter(transactions)
    }

    override fun getBundle(): Bundle = Bundle.EMPTY

    override fun getViewModelType() = MainViewModel::class.java

    override fun getLayoutId() = R.layout.activity_main
}