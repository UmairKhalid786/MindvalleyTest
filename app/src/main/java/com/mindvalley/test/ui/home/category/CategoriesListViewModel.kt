package com.mindvalley.test.ui.home.category

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.mindvalley.test.base.BaseListViewModel
import com.mindvalley.test.model.responses.categories.Category

class CategoriesListViewModel(
    private val repository: CategoriesRepository
) : BaseListViewModel() {

    val catObserver: MutableLiveData<ArrayList<Category>> = MutableLiveData()

    init {
        loadAll()
    }

    override fun loadAll(forceRefresh: Boolean) {
        if (forceRefresh) {
            repository.clearAll()
        }

        subscription = repository.loadAll()
            .doOnSubscribe { onRetrieveArticleListStart() }
            .doOnTerminate { onRetrieveArticleListFinish() }
            .subscribe(
                { result ->
                    Log.e("Result", result.toString())
                    onRetrieveArticleListSuccess((result as ArrayList<Category>))
                },
                { it ->
                    Log.e("Error", it.message)
                    onErrorLoading()
                }
            )
    }

    private fun onRetrieveArticleListSuccess(cat: ArrayList<Category>) {
        catObserver.postValue(cat)
    }
}