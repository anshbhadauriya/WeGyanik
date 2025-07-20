import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://wegyanik.in/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: ProductApiService = retrofit.create(ProductApiService::class.java)
}
