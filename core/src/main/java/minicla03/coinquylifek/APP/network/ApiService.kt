package minicla03.coinquylifek.APP.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiService @Inject constructor(
    private val tokenProvider: suspend () -> String?
) {
    fun <T> createService(serviceClass: Class<T>): T {
        val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(tokenProvider))
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://localhost:8080/") 
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(serviceClass)
    }
}