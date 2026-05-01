package com.example.airetailapp.data.repository

import android.util.Log
import com.example.airetailapp.domain.model.Product
import com.example.airetailapp.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MockProductRepository : ProductRepository {
    private val mockProducts = listOf(
        Product(1, "Classic White Tee", "A comfortable classic cotton t-shirt.", 19.99, "https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?q=80&w=500", "Apparel"),
        Product(2, "Denim Jeans", "Durable and stylish blue denim jeans.", 49.99, "https://images.unsplash.com/photo-1542272604-787c3835535d?q=80&w=500", "Apparel"),
        Product(3, "Wireless Headphones", "Noise-canceling over-ear headphones.", 149.99, "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?q=80&w=500", "Electronics"),
        Product(4, "Smart Watch", "Track your fitness and stay connected.", 199.99, "https://images.unsplash.com/photo-1523275335684-37898b6baf30?q=80&w=500", "Electronics"),
        Product(5, "Leather Wallet", "Handcrafted genuine leather wallet.", 29.99, "https://images.unsplash.com/photo-1627123424574-724758594e93?q=80&w=500", "Accessories")
    )

    override fun getProducts(): Flow<List<Product>> {
        Log.d("MockProductRepository", "Providing ${mockProducts.size} products")
        return flowOf(mockProducts)
    }

    override fun getProductByIdFlow(id: Int): Flow<Product?> {
        return flowOf(mockProducts.find { it.id == id })
    }

    override suspend fun getProductById(id: Int): Product? {
        return mockProducts.find { it.id == id }
    }

    override suspend fun searchProducts(query: String): List<Product> {
        return mockProducts.filter { 
            it.name.contains(query, ignoreCase = true) || 
            it.description.contains(query, ignoreCase = true) 
        }
    }

    override suspend fun toggleProductLike(id: Int, isLiked: Boolean) {
        // Mock implementation doesn't need to do anything or could update in-memory list
    }
}
