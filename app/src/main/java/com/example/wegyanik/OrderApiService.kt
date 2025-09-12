import com.example.wegyanik.OrdersApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OrderApiService {

    @GET("api/orders")
    suspend fun getUserOrders(
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 20
    ): Response<OrdersApiResponse>
}