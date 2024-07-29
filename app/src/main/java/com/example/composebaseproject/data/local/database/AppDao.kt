package com.example.composebaseproject.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.composebaseproject.data.models.MovieDb
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {


    @Query("SELECT * FROM movie_table")
    fun getAll(): Flow<List<MovieDb>>

    @Insert
    fun insertAll(vararg movieItems: MovieDb)

    @Query("DELETE FROM movie_table WHERE id IN (:ids)")
    fun delete(ids: List<Int>)

//    @Query("UPDATE movie_table SET title = :newTitle WHERE id = :movieId")
//    suspend fun updateTitle(movieId: Int, newTitle: String)


}