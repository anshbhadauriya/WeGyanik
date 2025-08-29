import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://wegyanik.in/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Optional: Keep for old code compatibility
    val api: ProductApiService by lazy {
        retrofit.create(ProductApiService::class.java)
    }

    // Specific APIs
    val productApi: ProductApiService by lazy {
        retrofit.create(ProductApiService::class.java)
    }

    val projectApi: ProjectApiService by lazy {
        retrofit.create(ProjectApiService::class.java)
    }
    val authApi: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
