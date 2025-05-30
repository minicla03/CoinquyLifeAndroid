package minicla03.coinquylifek.APP

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val tokenProvider: suspend () -> String?) : Interceptor
{
    override fun intercept(chain: Interceptor.Chain): Response
    {
        val originalRequest = chain.request()
        val token = runBlocking { tokenProvider() }

        val modifiedRequest = originalRequest.newBuilder().apply {
            token?.let {
                addHeader("Authorization", "Bearer $it")
            }
        }.build()

        return chain.proceed(modifiedRequest)
    }
}

