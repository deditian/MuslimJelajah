package com.dedi.muslimjelajah.di

import android.content.Context
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
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object MyModule {
    // get api
    @Provides
    fun provideDataSource(): MuslimDataSource {
        val service = Services.create("https://api.npoint.io/")
        return MuslimDataSourceImpl(service)
    }

    // manage data from get api
   @Provides
    fun provideRepository(remoteDataSource: MuslimDataSource,
                          localDataSource: AppDao) : MuslimRepository{
        return  MuslimRepositoryImpl(remoteDataSource, localDataSource)
    }

    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)


    @Provides
    fun provideDao(db: AppDatabase) = db.appDao()

}