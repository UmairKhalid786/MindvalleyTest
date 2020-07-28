package com.mindvalley.test.ui.home.category

import android.os.AsyncTask
import com.mindvalley.test.base.BaseRepository
import com.mindvalley.test.model.CategoriesDao
import com.mindvalley.test.model.responses.categories.Category
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CategoriesRepository(private val categoriesDao: CategoriesDao) :
    BaseRepository() {

    fun loadAll(forceRefresh: Boolean = false): Observable<List<Category>> {
        if (forceRefresh) {
            AsyncTask.execute {
                categoriesDao.clearAll()
            }
        }

        return Observable.fromCallable { categoriesDao.all }
            .concatMap { dbMindValley ->
                if (dbMindValley.isEmpty())
                    mindValleyApi.getCategories().concatMap { apiArticleResponse ->
                        categoriesDao.insertAll(apiArticleResponse.data.categories)
                        Observable.just(apiArticleResponse.data.categories)
                    }
                else
                    Observable.just(dbMindValley)
            }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun clearAll() {
        AsyncTask.execute {
            categoriesDao.clearAll()
        }
    }
}