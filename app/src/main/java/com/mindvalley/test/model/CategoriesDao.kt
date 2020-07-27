package com.mindvalley.test.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.mindvalley.test.model.responses.categories.Category

@Dao
interface CategoriesDao {
    @get:Query("SELECT * FROM Category")
    val all: List<Category>

    @Insert
    fun insert(vararg category: Category)
    
    @Transaction
    @Insert
    fun insertAll(categories: List<Category>)

    @Query("DELETE FROM Category")
    fun clearAll()
}