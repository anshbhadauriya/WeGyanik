package com.example.wegyanik

object ProductRepository {
    private var cachedProducts: List<Product>? = null

    fun getCachedProducts(): List<Product>? = cachedProducts

    fun saveProducts(products: List<Product>) {
        cachedProducts = products
    }

    fun clearCache() {
        cachedProducts = null
    }
}
