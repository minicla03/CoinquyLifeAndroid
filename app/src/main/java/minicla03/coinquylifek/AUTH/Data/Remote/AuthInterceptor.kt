package minicla03.coinquylifek.AUTH.Data.Remote

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AuthInterceptor(private val tokenProvider: suspend () -> String?) : Interceptor
{
    override fun intercept(chain: Interceptor.Chain): Response
    {
        val token = runBlocking { tokenProvider() }  // blocca coroutine per intercettore sync
        val requestBuilder: Request.Builder = chain.request().newBuilder()
        token?.let
        {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }
        return chain.proceed(requestBuilder.build())
    }
}

object RetrofitInstance
{
    private const val BASE_URL = "https://api.tuo-backend.com/"

    fun create(tokenProvider: suspend () -> String?): AuthApi {
        val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(tokenProvider))
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(AuthApi::class.java)
    }
}
