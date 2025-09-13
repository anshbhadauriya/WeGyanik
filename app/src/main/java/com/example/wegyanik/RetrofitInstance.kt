import com.example.wegyanik.App
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.Cache
import java.io.File

object RetrofitInstance {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    // 5 MB cache stored in app's cache directory
    private val cacheSize = (5 * 1024 * 1024).toLong()
    private val cache = Cache(App.instance.cacheDir, cacheSize)

    private val client = OkHttpClient.Builder()
        .cache(cache)
        .addInterceptor(loggingInterceptor)
        .addNetworkInterceptor { chain ->
            val response = chain.proceed(chain.request())
            // Cache responses for 1 minute
            response.newBuilder()
                .header("Cache-Control", "public, max-age=60")
                .build()
        }
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

    val authApi: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
