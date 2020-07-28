package com.mindvalley.test.ui.home.episode

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import com.mindvalley.test.model.ChannelDao
import com.mindvalley.test.model.EpisodesDao
import com.mindvalley.test.model.database.AppDatabase
import com.mindvalley.test.ui.home.channel.ChannelsRepository
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class EpisodsRepositoryTest {

    @InjectMocks
    var repository: EpisodsRepository? = null

    @Mock
    var dao: EpisodesDao? = null

    @Rule
    @JvmField
    var mockitoRule = MockitoJUnit.rule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this);
        AppDatabase.TEST_MODE = true
        dao = AppDatabase.getInstance(ApplicationProvider.getApplicationContext<Application>()).episodesDao()
    }

    @Test
    fun loadAll() {
        //TODO: Writting test here
    }
}