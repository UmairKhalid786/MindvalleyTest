package com.mindvalley.test.model;

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.mindvalley.test.model.responses.episodes.Episode
import com.mindvalley.test.model.responses.categories.Category


@Dao
interface EpisodesDao {
    @get:Query("SELECT * FROM Episode")
    val all: List<Episode>

    @Insert
    fun insert(vararg episode: Episode)

    @Transaction
    @Insert
    fun insertAll(episodes: List<Episode>)

    @Query("DELETE FROM Episode")
    fun clearAll()
}
