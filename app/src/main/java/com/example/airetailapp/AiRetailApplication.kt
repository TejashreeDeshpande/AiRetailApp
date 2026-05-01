package com.example.airetailapp

import android.app.Application
import com.example.airetailapp.di.appModule
import com.example.airetailapp.data.repository.MockProductRepository
import com.example.airetailapp.data.repository.ProductRepositoryImpl
import com.example.airetailapp.domain.repository.ProductRepository
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AiRetailApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@AiRetailApplication)
            modules(appModule)
        }

        // Seed database with mock data if empty
        MainScope().launch {
            val repository = get<ProductRepository>()
            if (repository is ProductRepositoryImpl) {
                // We'll check if empty first in a real app, but for now just seed it
                // Using the mock data from MockProductRepository
                repository.seedDatabase(
                    listOf(
                        com.example.airetailapp.domain.model.Product(1, "Classic White Tee", "A comfortable classic cotton t-shirt.", 19.99, "https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?q=80&w=500", "Apparel"),
                        com.example.airetailapp.domain.model.Product(2, "Denim Jeans", "Durable and stylish blue denim jeans.", 49.99, "https://images.unsplash.com/photo-1542272604-787c3835535d?q=80&w=500", "Apparel"),
                        com.example.airetailapp.domain.model.Product(3, "Wireless Headphones", "Noise-canceling over-ear headphones.", 149.99, "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?q=80&w=500", "Electronics"),
                        com.example.airetailapp.domain.model.Product(4, "Smart Watch", "Track your fitness and stay connected.", 199.99, "https://images.unsplash.com/photo-1523275335684-37898b6baf30?q=80&w=500", "Electronics"),
                        com.example.airetailapp.domain.model.Product(5, "Leather Wallet", "Handcrafted genuine leather wallet.", 29.99, "https://images.unsplash.com/photo-1627123424574-724758594e93?q=80&w=500", "Accessories")
                    )
                )
            }
        }
    }
}
