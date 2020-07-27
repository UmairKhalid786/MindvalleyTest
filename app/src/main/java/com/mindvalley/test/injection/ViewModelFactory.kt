package com.mindvalley.test.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import androidx.appcompat.app.AppCompatActivity
import com.mindvalley.test.model.database.AppDatabase
import com.mindvalley.test.ui.home.category.CategoriesRepository
import com.mindvalley.test.ui.home.category.CategoriesListViewModel
import com.mindvalley.test.ui.home.channel.ChannelsRepository
import com.mindvalley.test.ui.home.channel.ChannelsListViewModel
import com.mindvalley.test.ui.home.episode.EpisodesListViewModel
import com.mindvalley.test.ui.home.episode.EpisodsRepository

class ViewModelFactory(private val activity: AppCompatActivity) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val db = Room.databaseBuilder(
            activity.applicationContext,
            AppDatabase::class.java,
            "mindvalleydb"
        ).build()

        if (modelClass.isAssignableFrom(CategoriesListViewModel::class.java)) {
            val catRepo =
                CategoriesRepository(db.categoryDao())
            @Suppress("UNCHECKED_CAST")
            return CategoriesListViewModel(
                catRepo
            ) as T
        } else if (modelClass.isAssignableFrom(ChannelsListViewModel::class.java)) {
            val channelsRepo =
                ChannelsRepository(db.channelDao())
            @Suppress("UNCHECKED_CAST")
            return ChannelsListViewModel(
                channelsRepo
            ) as T
        } else if (modelClass.isAssignableFrom(EpisodesListViewModel::class.java)) {
            val episodsRepo =
                EpisodsRepository(db.episodesDao())
            @Suppress("UNCHECKED_CAST")
            return EpisodesListViewModel(
                episodsRepo
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}