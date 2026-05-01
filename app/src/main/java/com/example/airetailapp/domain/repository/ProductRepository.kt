package com.example.airetailapp.domain.repository

import com.example.airetailapp.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProducts(): Flow<List<Product>>
    suspend fun getProductById(id: Int): Product?
    suspend fun searchProducts(query: String): List<Product>
    suspend fun toggleProductLike(id: Int, isLiked: Boolean)
}
