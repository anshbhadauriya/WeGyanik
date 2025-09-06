import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object RetrofitInstance {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://wegyanik.in/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Optional: Keep for old code compatibility
    val api: ProductApiService by lazy {
        retrofit.create(ProductApiService::class.java)
    }

    // Specific APIs
//    val productApi: ProductApiService by lazy {
//        retrofit.create(ProductApiService::class.java)
//    }

//    val projectApi: ProjectApiService by lazy {
//        retrofit.create(ProjectApiService::class.java)
//    }

    val authApi: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
