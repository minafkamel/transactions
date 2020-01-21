package com.minafkamel.transactions.ui.base

import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.vikingsen.inject.viewmodel.savedstate.SavedStateViewModelFactory
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
Base activity for all activities in the app.
A [BaseViewModel] should be also declared via generic parameter <VN: BaseViewModel> to provide an instance when activity is created.
 */
abstract class BaseActivity<VM : BaseViewModel> : DaggerAppCompatActivity(), LifecycleOwner {

    @Inject
    lateinit var viewModelFactoryFactory: SavedStateViewModelFactory.Factory

    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

        viewModel = ViewModelProvider(this, viewModelFactoryFactory.create(this, getBundle())).get(getViewModelType())
        lifecycle.addObserver(viewModel)
        observeLiveData()
    }

    abstract fun observeLiveData()

    abstract fun getBundle(): Bundle?

    abstract fun getViewModelType(): Class<VM>

    abstract fun getLayoutId(): Int
}