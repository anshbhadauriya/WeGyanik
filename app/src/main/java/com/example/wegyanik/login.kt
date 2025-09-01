import com.google.gson.annotations.SerializedName

data class LoginRequest(
    val email: String,
    val password: String
)


data class LoginResponse(
    @SerializedName("message") val message: String,
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("role") val role: String
)