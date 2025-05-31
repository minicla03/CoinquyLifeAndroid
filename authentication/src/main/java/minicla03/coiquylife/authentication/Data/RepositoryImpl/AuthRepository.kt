package minicla03.coiquylife.authentication.Data.RepositoryImpl

import minicla03.coiquylife.authentication.Data.Remote.AuthRemoteDataSource
import minicla03.coiquylife.authentication.Data.Response.AuthResult
import minicla03.coiquylife.authentication.Domain.Model.User
import minicla03.coiquylife.authentication.domain.repository.IAuthRepository
import retrofit2.Response
import javax.inject.Inject

class AuthRepository @Inject constructor(
private val authRemoteDataSource: AuthRemoteDataSource
) : IAuthRepository
{
    override suspend fun login(user: User): Response<AuthResult>
    {
        return authRemoteDataSource.login(user)
    }

    override suspend fun register(user: User): Response<AuthResult>
    {
        return authRemoteDataSource.register(user)
    }
}