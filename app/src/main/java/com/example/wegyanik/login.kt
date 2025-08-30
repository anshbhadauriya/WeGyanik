
data class LoginRequest(
    val email: String,
    val password: String
)


data class LoginResponse(
    val message: String,
    val id: String,
    val name: String,
    val email: String,
    val role: String
)
