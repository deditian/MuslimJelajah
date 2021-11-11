package com.dedi.muslimjelajah.di

import android.content.Context
import com.dedi.muslimjelajah.data.NewsServices
import com.dedi.muslimjelajah.data.Services
import com.dedi.muslimjelajah.data.local.AppDao
import com.dedi.muslimjelajah.data.local.AppDatabase
import com.dedi.muslimjelajah.data.source.MuslimDataSource
import com.dedi.muslimjelajah.data.source.MuslimDataSourceImpl
import com.dedi.muslimjelajah.repository.MuslimRepository
import com.dedi.muslimjelajah.repository.MuslimRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MyModule {
    // get api
    @Provides
    fun provideDataSource(): MuslimDataSource {
        val service = Services.create("https://api.npoint.io/")
        return MuslimDataSourceImpl(service)
    }

//    @Provides
//    fun provideDataSourceNews(): NewsDataSource {
//        val service = NewsServices.create("https://newsapi.org/v2/")
//
//        return NewsDataSourceImpl(service)
//    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(NewsServices.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideNewsApi(retrofit: Retrofit): NewsServices =
        retrofit.create(NewsServices::class.java)

    // manage data from get api
   @Provides
    fun provideRepository(@ApplicationContext mContext: Context,
                          remoteDataSource: MuslimDataSource,
                          localDataSource: AppDao) : MuslimRepository{
        return  MuslimRepositoryImpl(mContext,remoteDataSource, localDataSource)
    }

    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)


    @Provides
    fun provideDao(db: AppDatabase) = db.appDao()

}