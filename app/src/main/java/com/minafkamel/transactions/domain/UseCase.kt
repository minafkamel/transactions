package com.minafkamel.transactions.domain

import io.reactivex.Single

/**
A base use case for all use cases in the app
 */
interface UseCase<Params, Result> {

    fun build(params: Params): Single<Result>
}
