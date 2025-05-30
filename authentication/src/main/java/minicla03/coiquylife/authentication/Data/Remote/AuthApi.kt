package minicla03.coiquylife.authentication.Data.Remote

import retrofit2.Call
import minicla03.coiquylife.authentication.Data.Response.AuthResult
import minicla03.coiquylife.authentication.Domain.Model.User
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi
{
    @POST("auth/login")
    fun login(email: String?, password: String?): AuthResult

    @POST("auth/register")
    fun register(@Body user: User?): AuthResult

}
