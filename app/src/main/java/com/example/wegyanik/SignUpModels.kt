package com.example.wegyanik

// Request body for registration
data class RegisterRequest(
    val email: String,
    val password: String,
    val name: String
)

// Response from the API
data class RegisterResponse(
    val message: String
)
