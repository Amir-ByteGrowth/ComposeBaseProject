package com.example.composebaseproject.repository

import com.example.composebaseproject.MyApplication
import com.example.composebaseproject.R
import com.example.composebaseproject.data.local.database.AppDao
import com.example.composebaseproject.data.local.datastore.DataStoreProvider
import com.example.composebaseproject.data.models.MotivationDataEntity
import com.example.composebaseproject.data.models.Suggestion
import com.example.composebaseproject.data.models.UserData
import com.example.composebaseproject.data.remote.ApiService
import com.example.composebaseproject.data.remote.Resource
import com.example.composebaseproject.data.remote.ResponseTemplate
import com.example.composebaseproject.utils.extractErrorMessage
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService,
    private val localDataSource: AppDao,
    private val dataStoreProvider: DataStoreProvider,
) {
    /////// New Approach /////////////

    suspend fun getUserList() = apiService.getUserList()

    suspend fun getUserListNewApproach(): Resource<ResponseTemplate<List<UserData>>> {


        try {
            val c = apiService.getUserList()
            c.let {
                if (c.isSuccessful) {

                    return Resource.success(c.body()!!)

                } else if (it.code() == 401 || it.code() == 400 || it.code() == 402 || it.code() == 403) {
                    val errorMessagesJson = it.errorBody()?.source()?.buffer?.readUtf8()!!

                    return Resource.error(
                        "", null, extractErrorMessage(
                            errorMessagesJson
                        )
                    )

                } else {
                    return Resource.error(
                        MyApplication.applicationContext.getString(R.string.something_went_wrong),
                        null, null
                    )

                }
            }
        } catch (e: Exception) {
            return Resource.error(
                e.message!!, null, null
            )

        }
    }


    suspend fun getUserListGenericResponseHandle(): Resource<ResponseTemplate<List<UserData>>> {
        return handleApiResponse { apiService.getUserList() }
    }


    private suspend fun <T> handleApiResponse(apiCall: suspend () -> Response<T>): Resource<T> {
        return try {
            val c = apiCall()
            c.let {
                if (c.isSuccessful) {

                    return Resource.success(c.body()!!)

                } else if (it.code() == 401 || it.code() == 400 || it.code() == 402 || it.code() == 403) {
                    val errorMessagesJson = it.errorBody()?.source()?.buffer?.readUtf8()!!

                    return Resource.error(
                        "", null, extractErrorMessage(
                            errorMessagesJson
                        )
                    )

                } else {
                    return Resource.error(
                        MyApplication.applicationContext.getString(R.string.something_went_wrong),
                        null, null
                    )

                }
            }
        } catch (e: Exception) {
            Resource.error(e.message!!, null, null)
        }
    }


    ////Data Store Provider///////

    val getShowIntro = dataStoreProvider.showIntroScreenFlow
    suspend fun setShowIntro(value: Boolean) = dataStoreProvider.saveIntroScreen(value)

    /////


//    suspend fun getMotivationList() = apiService.getMotivationList()
//
//    fun getAllMotivation() = localDataSource.getAllMotivation()
//
//    suspend fun insertMotivation(motivationDataEntity: MotivationDataEntity) =
//        localDataSource.insertQrCode(motivationDataEntity = motivationDataEntity)
//
//
//    suspend fun insertSuggestion(suggestion: Suggestion) {
//        localDataSource.insertSuggestion(suggestion)
//    }
//
//    suspend fun getSuggestions(handle: String = "a"): List<Suggestion> {
//        return localDataSource.getSuggestions(handle)
//    }
//
//    fun getSuggestionsList(handle: String = "a"): Flow<List<Suggestion>> {
//        return localDataSource.getSuggestionsList()
//    }

}