package com.mindvalley.test.ui.home.channel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.mindvalley.test.base.BaseListViewModel
import com.mindvalley.test.model.responses.channels.Channel

class ChannelsListViewModel(
    private val repository: ChannelsRepository
) : BaseListViewModel() {

    init {
        loadAll()
    }

    val chanObserver: MutableLiveData<ArrayList<Channel>> = MutableLiveData()

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
                    onRetrieveChannelListSuccess((result as ArrayList<Channel>))
                },
                { it ->
                    Log.e("Error", it.message)
                    onErrorLoading()
                }
            )
    }

    private fun onRetrieveChannelListSuccess(channels: ArrayList<Channel>) {
        chanObserver.postValue(channels)
    }
}