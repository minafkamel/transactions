package com.minafkamel.transactions.di

import com.minafkamel.transactions.TransactionsApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        ActivityInjectorModule::class,
        ApiInjectorModule::class,
        VMModule::class,
        ApplicationInjectorModule::class]
)
interface ApplicationComponent : AndroidInjector<TransactionsApplication> {

    @Component.Builder
    interface Builder {

        fun build(): ApplicationComponent

        @BindsInstance
        fun bindApplication(application: TransactionsApplication): Builder
    }
}