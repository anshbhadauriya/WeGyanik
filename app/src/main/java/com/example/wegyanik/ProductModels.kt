package com.example.wegyanik

data class ProductApiResponse(
    val success: Boolean,
    val data: List<Product>,
    val message: String
)

data class Product(
    val id: String,
    val name: String,
    val slug: String,
    val description: String,
    val originalPrice: Int,
    val discountedPrice: Int,
    val stock: Int,
    val gallery: List<String>,
    val features: List<String>,
    val specifications: List<Specification>,
    val category: Category
)

data class Specification(
    val name: String,
    val value: String
)

data class Category(
    val id: String,
    val name: String
)
