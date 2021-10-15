package com.dedi.muslimjelajah.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.dedi.muslimjelajah.data.Services
import com.dedi.muslimjelajah.data.source.MuslimDataSource
import com.dedi.muslimjelajah.data.source.MuslimDataSourceImpl
import com.dedi.muslimjelajah.db.AppConstants.BASE_URL
import com.dedi.muslimjelajah.db.AppConstants.DATABASE_NAME
import com.dedi.muslimjelajah.db.AppDatabase
import com.dedi.muslimjelajah.db.dao.AppDao
import com.dedi.muslimjelajah.repository.networkdata.MuslimRepository
import com.dedi.muslimjelajah.repository.networkdata.MuslimRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MyModule {
    // get api
    @Provides
    @Singleton
    fun provideDataSource(): MuslimDataSource {
        val service = Services.create(BASE_URL)
        return MuslimDataSourceImpl(service)
    }

    // manage data from get api
    @Provides
    @Singleton
    fun provideRepository(dataSource: MuslimDataSource, dao: AppDao): MuslimRepository {
        return MuslimRepositoryImpl(dataSource,dao)
    }

    //room
    @Singleton
    @Provides
    fun provideRoomInstance(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        DATABASE_NAME
    ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideAppDao(db: AppDatabase) = db.getAppDao()

}