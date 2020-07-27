package com.mindvalley.test.model;

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.mindvalley.test.model.responses.channels.Channel


@Dao
interface ChannelDao {
    @get:Query("SELECT * FROM Channel")
    val all: List<Channel>

    @Insert
    fun insert(vararg channel: Channel)

    @Transaction
    @Insert
    fun insertAll(channels: List<Channel>)

    @Query("DELETE FROM Channel")
    fun clearAll()
}
