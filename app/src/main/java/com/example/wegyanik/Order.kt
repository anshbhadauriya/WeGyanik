package com.example.wegyanik

// Data classes to match JSON response
data class OrdersApiResponse(
    val orders: List<Order>,
    val hasMore: Boolean,
    val total: Int,
    val currentPage: Int,
    val totalPages: Int
)

data class Order(
    val id: String,
    val productName: String,
    val quantity: Int,
    val price: Double,
    val orderDate: String
    // Add other fields as per your API
)
