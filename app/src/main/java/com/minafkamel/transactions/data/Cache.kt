package com.minafkamel.transactions.data

import io.reactivex.Completable
import io.reactivex.Maybe
import javax.inject.Inject
import javax.inject.Singleton

/**
Simple caching mechanism to minimise BE hitting and support other storing functionality
 */
@Suppress("UNCHECKED_CAST")
@Singleton
class Cache @Inject constructor() {

    private val cacheMap = HashMap<String, Any?>()

    fun <T> getItem(key: String): Maybe<T> {
        val value = cacheMap[key] as T
        return if (value != null) Maybe.just(value) else Maybe.empty()
    }

    fun <T> put(key: String, value: T): Completable {
        return Completable.fromAction { cacheMap[key] = value }
    }
}