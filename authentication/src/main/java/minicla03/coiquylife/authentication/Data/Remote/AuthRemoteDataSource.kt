package minicla03.coiquylife.authentication.Data.Remote

import minicla03.coinquylifek.APP.ApiService
import minicla03.coiquylife.authentication.Data.IDataSource
import minicla03.coiquylife.authentication.Data.Response.AuthResult
import minicla03.coiquylife.authentication.Domain.Model.User

class AuthRemoteDataSource: IDataSource
{
    private val apiEndpoints = ApiService.createService(
        AuthApi::class.java,
        tokenProvider = { null }
    )
    
    override suspend fun login(email: String, password: String): AuthResult
    {
        return apiEndpoints.login(email, password)
    }

    override suspend fun register(user: User): AuthResult
    {
        return apiEndpoints.register(user)
    }
}
