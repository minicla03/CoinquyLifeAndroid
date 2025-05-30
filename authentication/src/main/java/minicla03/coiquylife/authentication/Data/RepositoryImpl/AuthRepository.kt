package minicla03.coiquylife.authentication.Data.RepositoryImpl

import minicla03.coiquylife.authentication.Data.Remote.AuthRemoteDataSource
import minicla03.coiquylife.authentication.Data.Response.AuthResult
import minicla03.coiquylife.authentication.Domain.Model.User
import minicla03.coiquylife.authentication.Domain.Repository.IAuthRepository

class AuthRepository() : IAuthRepository
{
    private val authRemoteDataSource: AuthRemoteDataSource = AuthRemoteDataSource()
    //private val authLocalDataSource: AuthLocalDataSource = AuthLocalDataSource()

    override suspend fun login(usernameOrEmail: String?, password: String?): AuthResult
    {
        return authRemoteDataSource.login(usernameOrEmail, password)
    }

    override suspend fun register(user: User): AuthResult
    {
        return authRemoteDataSource.register(user)
    }
}