package minicla03.coiquylife.authentication.Data.Remote

import minicla03.coinquylifek.APP.ApiService
import minicla03.coiquylife.authentication.Data.Response.AuthResult
import minicla03.coiquylife.authentication.Domain.Model.User
import retrofit2.Call

class AuthRemoteDataSource
{
    private val apiEndpoints = ApiService.createService(
        AuthApi::class.java,
        tokenProvider = { null }
    )
    
    fun login(email: String, password: String): Call<AuthResult?>?
    {
        return apiEndpoints.login(email, password)  
    }

    fun register(user: User): Call<AuthResult?>?
    {
        return apiEndpoints.register(user)
    }
}
