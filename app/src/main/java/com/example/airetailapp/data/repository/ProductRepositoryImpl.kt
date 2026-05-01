package com.example.airetailapp.data.repository

import com.example.airetailapp.data.local.ProductDao
import com.example.airetailapp.data.local.toDomain
import com.example.airetailapp.data.local.toEntity
import com.example.airetailapp.domain.model.Product
import com.example.airetailapp.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProductRepositoryImpl(
    private val productDao: ProductDao
) : ProductRepository {

    override fun getProducts(): Flow<List<Product>> {
        return productDao.getAllProducts().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getProductByIdFlow(id: Int): Flow<Product?> {
        return productDao.getProductByIdFlow(id).map { it?.toDomain() }
    }

    override suspend fun getProductById(id: Int): Product? {
        return productDao.getProductById(id)?.toDomain()
    }

    override suspend fun searchProducts(query: String): List<Product> {
        return productDao.searchProducts(query).map { it.toDomain() }
    }

    override suspend fun toggleProductLike(id: Int, isLiked: Boolean) {
        productDao.updateLikedStatus(id, isLiked)
    }

    // Helper to seed data from Mock if needed
    suspend fun seedDatabase(products: List<Product>) {
        productDao.insertProducts(products.map { it.toEntity() })
    }
}
