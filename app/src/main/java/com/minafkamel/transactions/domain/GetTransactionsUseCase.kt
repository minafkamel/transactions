package com.minafkamel.transactions.domain

import com.minafkamel.transactions.data.entities.TransactionsRaw
import com.minafkamel.transactions.data.repositories.TransactionsRepository
import io.reactivex.Single
import javax.inject.Inject

class GetTransactionsUseCase @Inject constructor(
    private val transactionsRepository: TransactionsRepository
) : UseCase<GetTransactionsUseCase.Params, TransactionsRaw?> {

    override fun build(params: Params): Single<TransactionsRaw?> {
        return transactionsRepository.getTransactions()
    }

    class Params
}

