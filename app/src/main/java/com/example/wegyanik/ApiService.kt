import com.example.wegyanik.RegisterRequest
import com.example.wegyanik.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @POST("api/register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>

    @POST("api/auth/signin/credentials")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>
}

