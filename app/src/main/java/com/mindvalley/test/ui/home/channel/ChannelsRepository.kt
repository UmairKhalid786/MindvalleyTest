package com.mindvalley.test.ui.home.channel

import android.os.AsyncTask
import com.mindvalley.test.base.BaseRepository
import com.mindvalley.test.model.ChannelDao
import com.mindvalley.test.model.responses.channels.Channel
import com.mindvalley.test.network.MindValleyApi
import io.reactivex.Observable
import javax.inject.Inject

class ChannelsRepository(private val channelDao: ChannelDao ) :
    BaseRepository() {

    fun loadAll(forceRefresh: Boolean = false) : Observable<List<Channel>> {
        if (forceRefresh) {
            AsyncTask.execute {
                 channelDao.clearAll()
            }
        }

       return Observable.fromCallable { channelDao.all }
            .concatMap { dbMindValley ->
                if (dbMindValley.isEmpty())
                    mindValleyApi.getChannels().concatMap { apiArticleResponse ->
                        channelDao.insertAll(apiArticleResponse.data.channels)
                        Observable.just(apiArticleResponse.data.channels)
                    }
                else
                    Observable.just(dbMindValley)
            }
    }

    fun clearAll() {
        AsyncTask.execute({
            channelDao.clearAll()
        })
    }
}