package com.dedi.muslimjelajah.di

import android.app.Application
import com.dedi.muslimjelajah.view.menu.MenuRepository
import com.dedi.muslimjelajah.view.menu.MenuRepositoryImpl
import com.dedi.muslimjelajah.view.menu.MenuViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApp: Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@MyApp)
            modules(appModule)
        }

    }
    private val appModule = module {

        single<MenuRepository> { MenuRepositoryImpl(get()) }

        // MyViewModel ViewModel
        viewModel { MenuViewModel(get()) }
    }
}