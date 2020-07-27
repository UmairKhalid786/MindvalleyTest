package com.mindvalley.test.base;

import com.mindvalley.test.injection.component.DaggerMainInjector
import com.mindvalley.test.injection.component.MainInjector
import com.mindvalley.test.injection.module.NetworkModule
import com.mindvalley.test.network.MindValleyApi
import com.mindvalley.test.ui.home.category.CategoriesRepository
import javax.inject.Inject

open class BaseRepository() {

    @Inject
    lateinit var mindValleyApi: MindValleyApi

    private val injector: MainInjector = DaggerMainInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init{
        inject()
    }

    private fun inject() {
        when (this) {
            is CategoriesRepository -> injector.inject(this)
        }

        injector.inject(this)
    }
}
