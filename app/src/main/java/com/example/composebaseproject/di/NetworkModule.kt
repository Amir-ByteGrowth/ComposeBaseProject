package com.example.composemvvmhiltretrofit.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.composebaseproject.MyApplication
import com.example.composebaseproject.data.local.database.AppDao
import com.example.composebaseproject.data.local.database.AppDatabase
import com.example.composebaseproject.data.local.datastore.DataStoreProvider
import com.example.composebaseproject.data.remote.ApiService
import com.example.composebaseproject.repository.MainRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {



    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient{

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

      return  OkHttpClient.Builder()

            .addInterceptor(loggingInterceptor)
            .addInterceptor(
                ChuckerInterceptor.Builder(MyApplication.applicationContext)
                    .collector(ChuckerCollector(MyApplication.applicationContext))
                    .maxContentLength(250000L)
                    .redactHeaders(emptySet())
                    .alwaysReadResponseBody(false)
                    .build()
            )
//                .sslSocketFactory(
//                    sslContext.socketFactory,
//                    tmf.trustManagers[0] as X509TrustManager
//                )
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()



    }


    @Provides
    fun provideGson(): Gson = GsonBuilder().create()


    @Singleton
    @Provides
    fun providesRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.jsonbin.io")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideRepository(
        apiService: ApiService,
        localDataSource: AppDao, dataStoreProvider: DataStoreProvider
    ) =
        MainRepository(apiService, localDataSource = localDataSource, dataStoreProvider = dataStoreProvider)

    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context) = AppDatabase.getDatabase(context)

    @Singleton
    @Provides
    fun provideDao(db: AppDatabase) = db.appDao()


    @Singleton
    @Provides
    fun providesDataStore(@ApplicationContext appContext: Context) = DataStoreProvider(appContext)

}