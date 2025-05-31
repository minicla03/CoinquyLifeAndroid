package minicla03.coiquylife.authentication.Data.RepositoryImpl

import minicla03.coinquylifek.APP.security.TokenManager
import minicla03.coiquylife.authentication.Data.Remote.AuthRemoteDataSource
import minicla03.coiquylife.authentication.Data.Response.AuthResult
import minicla03.coiquylife.authentication.Domain.Model.User
import minicla03.coiquylife.authentication.domain.repository.IAuthRepository
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val tokenManager: TokenManager
    private val appPreferences: AppPreferences
) : IAuthRepository
{
    override suspend fun login(user: User): AuthResult
    {
        val authResult = authRemoteDataSource.login(user)
        tokenManager.saveToken(authResult.token)
        val username = authRemoteDataSource.getUser(tokenManager.getToken()) ?: return authResult)
        appPreferences.save("username", username)
        return authResult
    }

    override suspend fun register(user: User): AuthResult
    {
        return authRemoteDataSource.register(user)
    }
}