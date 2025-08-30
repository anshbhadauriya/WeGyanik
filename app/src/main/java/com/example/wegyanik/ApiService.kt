import com.example.wegyanik.RegisterRequest
import com.example.wegyanik.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("api/register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>
}
