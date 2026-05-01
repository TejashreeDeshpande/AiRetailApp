package com.example.airetailapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airetailapp.domain.model.Product
import com.example.airetailapp.domain.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProductViewModel(
    private val repository: ProductRepository
) : ViewModel() {

    val products: StateFlow<List<Product>> = repository.getProducts()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = emptyList()
        )

    private val _searchResult = MutableStateFlow<List<Product>>(emptyList())
    val searchResult = _searchResult.asStateFlow()

    fun searchProducts(query: String) {
        viewModelScope.launch {
            _searchResult.value = repository.searchProducts(query)
        }
    }

    fun toggleLike(product: Product) {
        viewModelScope.launch {
            repository.toggleProductLike(product.id, !product.isLiked)
        }
    }
}
