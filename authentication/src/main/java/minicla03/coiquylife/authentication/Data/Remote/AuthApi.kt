package minicla03.coiquylife.authentication.data.remote

import minicla03.coiquylife.authentication.Data.Response.AuthResult
import minicla03.coiquylife.authentication.Domain.Model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("auth/login")
    suspend fun login(@Body user: User): Response<AuthResult>

    @POST("auth/register")
    suspend fun register(@Body user: User): Response<AuthResult>

    @POST("gateway/verify-token")
    suspend fun verifyToken(@Body body: Map<String, String>): Response<Map<String, String>>
}
