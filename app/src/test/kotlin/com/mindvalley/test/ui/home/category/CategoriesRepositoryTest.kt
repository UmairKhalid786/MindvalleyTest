package com.mindvalley.test.ui.home.category

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import com.mindvalley.test.model.CategoriesDao
import com.mindvalley.test.model.database.AppDatabase
import com.mindvalley.test.model.responses.categories.Category
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class CategoriesRepositoryTest {

    @InjectMocks
    var categoriesRepository: CategoriesRepository? = null

    @Mock
    var categoryDao: CategoriesDao? = null

    @Rule
    @JvmField
    var mockitoRule = MockitoJUnit.rule()

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this);
        AppDatabase.TEST_MODE = true
        categoryDao = AppDatabase.getInstance(ApplicationProvider.getApplicationContext<Application>()).categoryDao()
    }

    @Test
    fun loadAll() {
        //Will be writting test here soon
    }

    @Test
    fun clearAll() {
    }
}