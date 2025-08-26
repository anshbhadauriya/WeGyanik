package com.example.wegyanik

data class Order(
    val id: String,
    val productName: String,
    val quantity: Int,
    val price: Double,
    val orderDate: String
)
