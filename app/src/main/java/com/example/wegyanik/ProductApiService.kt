import com.example.wegyanik.ProductApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProductApiService {
    @GET("api/product")
    suspend fun getProducts(): Response<ProductApiResponse>
}
