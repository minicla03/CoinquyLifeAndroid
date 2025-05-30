package minicla03.coinquylifek.APP

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService private constructor()
{
    companion object
    {
        private const val BASE_URL = "http://localhost:8080/"

        fun <T> createService(serviceClass: Class<T>, tokenProvider: suspend () -> String?): T
        {
            val client = OkHttpClient.Builder()
                .addInterceptor(AuthInterceptor(tokenProvider))
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(serviceClass)
        }
    }
}
