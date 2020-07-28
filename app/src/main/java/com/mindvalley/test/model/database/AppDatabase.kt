package com.mindvalley.test.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mindvalley.test.model.CategoriesDao
import com.mindvalley.test.model.ChannelDao
import com.mindvalley.test.model.EpisodesDao
import com.mindvalley.test.model.converters.*
import com.mindvalley.test.model.responses.episodes.Episode
import com.mindvalley.test.model.responses.categories.Category
import com.mindvalley.test.model.responses.channels.Channel

@Database(entities = [Category::class, Episode::class, Channel::class], version = 1)
@TypeConverters(
    AssetConverter::class,
    IconAssetConverter::class,
    LatestMediaConverter::class,
    SeriesConverter::class,
    ChannelTypeConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoriesDao
    abstract fun episodesDao(): EpisodesDao
    abstract fun channelDao(): ChannelDao

    companion object {
        var TEST_MODE = false
        private val databaseName = "mindvalleydb"

        private var db: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (db == null) {
                if (TEST_MODE) {
                    db = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        databaseName
                    ).allowMainThreadQueries().build()
                } else {
                    db = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        databaseName
                    ).build()
                }
            }
            return db as AppDatabase
        }

        private fun close() {
            db?.close()
        }
    }
}