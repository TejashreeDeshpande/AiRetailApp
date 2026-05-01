package com.example.airetailapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.airetailapp.domain.model.Product

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String,
    val category: String,
    val isLiked: Boolean = false
)

fun ProductEntity.toDomain(): Product {
    return Product(id, name, description, price, imageUrl, category, isLiked)
}

fun Product.toEntity(): ProductEntity {
    return ProductEntity(id, name, description, price, imageUrl, category, isLiked)
}
