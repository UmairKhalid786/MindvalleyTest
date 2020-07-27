package com.mindvalley.test.ui.home.episode

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.mindvalley.test.base.BaseListViewModel
import com.mindvalley.test.model.responses.episodes.Episode


class EpisodesListViewModel(
    private val repository: EpisodsRepository
) : BaseListViewModel() {

    val episodesObserver: MutableLiveData<ArrayList<Episode>> = MutableLiveData()

    init {
        loadAll()
    }

    override fun loadAll(forceRefresh: Boolean) {
        if (forceRefresh) {
            repository.clearAll()
        }

        subscription = repository.loadAll(forceRefresh)
            .doOnSubscribe { onRetrieveArticleListStart() }
            .doOnTerminate { onRetrieveArticleListFinish() }
            .subscribe(
                { result ->
                    Log.e("Result", result.toString())
                    onRetrieveEpisodesListSuccess((result as ArrayList<Episode>))
                },
                { it ->
                    Log.e("Error", it.message)
                    onErrorLoading()
                }
            )
    }

    private fun onRetrieveEpisodesListSuccess(channels: ArrayList<Episode>) {
        episodesObserver.postValue(channels)
    }
}