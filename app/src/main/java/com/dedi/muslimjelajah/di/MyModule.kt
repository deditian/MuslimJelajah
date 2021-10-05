package com.dedi.muslimjelajah.di

import com.dedi.muslimjelajah.data.Services
import com.dedi.muslimjelajah.data.source.MuslimDataSource
import com.dedi.muslimjelajah.data.source.MuslimDataSourceImpl
import com.dedi.muslimjelajah.repository.MuslimRepository
import com.dedi.muslimjelajah.repository.MuslimRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

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
    fun provideRepository(dataSource: MuslimDataSource): MuslimRepository {
        return MuslimRepositoryImpl(dataSource)
    }
}