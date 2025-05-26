package minicla03.coinquylifek.AUTH.Data.Remote

import android.telecom.Call

interface AuthApi
{
    @POST("auth/login")
    fun login(email: String?, password: String?): Call<AuthResult?>?

    @POST("auth/register")
    fun register(@Body user: User?): Call<AuthResult?>?

}
