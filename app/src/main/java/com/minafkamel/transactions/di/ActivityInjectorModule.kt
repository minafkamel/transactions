package com.minafkamel.transactions.di

import com.minafkamel.transactions.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityInjectorModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

}