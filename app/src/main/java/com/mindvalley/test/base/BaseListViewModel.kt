package com.mindvalley.test.base

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mindvalley.test.R
import com.mindvalley.test.injection.component.DaggerMainInjector
import com.mindvalley.test.injection.component.MainInjector
import com.mindvalley.test.injection.module.NetworkModule
import com.mindvalley.test.ui.home.category.CategoriesListViewModel
import io.reactivex.disposables.Disposable

abstract class BaseListViewModel : ViewModel() {

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    var subscription: Disposable? = null

    abstract fun loadAll(forceRefresh: Boolean = false)

    override fun onCleared() {
        super.onCleared()
        subscription?.dispose()
    }

    fun onRetrieveArticleListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    fun onRetrieveArticleListFinish() {
        loadingVisibility.value = View.GONE
    }

    fun onErrorLoading() {
        errorMessage.value = R.string.post_error
    }

    fun refreshStuff() {
        loadAll(true)
    }

}