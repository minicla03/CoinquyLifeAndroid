package minicla03.coiquylife.authentication.Data.RepositoryImpl

import minicla03.coiquylife.authentication.Data.Remote.AuthRemoteDataSource
import minicla03.coiquylife.authentication.Data.Response.AuthResult
import minicla03.coiquylife.authentication.Domain.Model.User
import minicla03.coiquylife.authentication.Domain.Repository.IAuthRepository
import retrofit2.Call

class AuthRepository() : IAuthRepository
{
    private val authRemoteDataSource: AuthRemoteDataSource = AuthRemoteDataSource()

    override suspend fun login(usernameOrEmail: String, password: String): Call<AuthResult?>?
    {
        return authRemoteDataSource.login(usernameOrEmail, password)
    }

    override suspend fun register(user: User): Call<AuthResult?>?
    {
        return authRemoteDataSource.register(user)
    }
}