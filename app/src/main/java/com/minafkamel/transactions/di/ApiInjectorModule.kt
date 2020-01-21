package com.minafkamel.transactions.di

import com.minafkamel.transactions.data.apis.TransactionsApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiInjectorModule {

    @Provides
    @Singleton
    fun providesTransactionsApi(retrofit: Retrofit): TransactionsApi {
        return retrofit.create(TransactionsApi::class.java)
    }
}