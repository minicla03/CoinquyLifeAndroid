package minicla03.coinquylifek.AUTH.Data.Remote

import minicla03.coinquylifek.APP.ApiService
import minicla03.coinquylifek.AUTH.Data.Response.AuthResult
import minicla03.coinquylifek.AUTH.Domain.Model.User
import retrofit2.Call

class AuthRemoteDataSource
{
    private val apiEndpoints = ApiService.createService(
        AuthApi::class.java,
        tokenProvider = { null } // Assuming no token is needed for these endpoints
    )
    
    fun login(email: String, password: String): Call<AuthResult?>?
    {
        return apiEndpoints.login(email, password)  
    }

    fun register(user:User): Call<AuthResult?>?
    {
        return apiEndpoints.register(user)
    }
}
