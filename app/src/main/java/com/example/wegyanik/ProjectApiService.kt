import com.example.wegyanik.Project
import retrofit2.Response
import retrofit2.http.GET

interface ProjectApiService {
    @GET("api/project")
    suspend fun getProjects(): Response<List<Project>>
}
