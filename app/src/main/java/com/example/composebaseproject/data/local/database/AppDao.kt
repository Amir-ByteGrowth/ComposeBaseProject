package com.example.composebaseproject.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.composebaseproject.data.models.MotivationDataEntity
import com.example.composebaseproject.data.models.MotivationDataItem
import com.example.composebaseproject.data.models.Suggestion
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {


    // to get list of qr codes dependent upon viewType either scanned or generated qr

//    @Query("SELECT * FROM motiviations_table")
//    fun getAllMotivation(): List<MotivationDataEntity>

    // below is the insert method for
    // adding a new entry to our database.
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun insertQrCode(motivationDataEntity: MotivationDataEntity)
//
////    //to insert suggestions
//    @Insert
//    suspend fun insertSuggestion(suggestion: Suggestion)



}