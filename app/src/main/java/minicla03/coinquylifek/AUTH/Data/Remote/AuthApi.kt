package minicla03.coinquylifek.AUTH.Data.Remote

import retrofit2.Call
import minicla03.coinquylifek.AUTH.Data.Response.AuthResult
import minicla03.coinquylifek.AUTH.Domain.Model.User
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi
{
    @POST("auth/login")
    fun login(email: String?, password: String?): Call<AuthResult?>?

    @POST("auth/register")
    fun register(@Body user: User?): Call<AuthResult?>?

}
