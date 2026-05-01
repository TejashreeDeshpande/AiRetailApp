package com.example.airetailapp.di

import androidx.room.Room
import com.example.airetailapp.data.local.AppDatabase
import com.example.airetailapp.data.repository.MockProductRepository
import com.example.airetailapp.data.repository.ProductRepositoryImpl
import com.example.airetailapp.domain.repository.ProductRepository
import com.example.airetailapp.ui.viewmodel.ProductViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "ai_retail_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    single { get<AppDatabase>().productDao() }

    // Using Room-based repository
    single<ProductRepository> { ProductRepositoryImpl(get()) }

    viewModel { ProductViewModel(get()) }
}
