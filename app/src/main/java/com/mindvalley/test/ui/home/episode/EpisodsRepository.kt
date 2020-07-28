package com.mindvalley.test.ui.home.episode

import android.os.AsyncTask
import com.mindvalley.test.base.BaseRepository
import com.mindvalley.test.model.ChannelDao
import com.mindvalley.test.model.EpisodesDao
import com.mindvalley.test.model.responses.channels.Channel
import com.mindvalley.test.model.responses.episodes.Episode
import com.mindvalley.test.network.MindValleyApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class EpisodsRepository(private val episodesDao: EpisodesDao) :
    BaseRepository() {

    fun loadAll(forceRefresh: Boolean = false): Observable<List<Episode>> {
        if (forceRefresh) {
            AsyncTask.execute {
                episodesDao.clearAll()
            }
        }

        return Observable.fromCallable { episodesDao.all }
            .concatMap { dbMindValley ->
                if (dbMindValley.isEmpty())
                    mindValleyApi.getEpisodes().concatMap { apiArticleResponse ->
                        episodesDao.insertAll(apiArticleResponse.data.media)
                        Observable.just(apiArticleResponse.data.media)
                    }
                else
                    Observable.just(dbMindValley)
            }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun clearAll() {
        AsyncTask.execute{
            episodesDao.clearAll()
        }
    }
}