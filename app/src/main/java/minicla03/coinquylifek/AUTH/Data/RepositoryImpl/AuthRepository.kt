package minicla03.coinquylifek.AUTH.Data.RepositoryImpl

import android.app.Application
import minicla03.coinquylifek.AUTH.Data.Remote.AuthRemoteDataSource
import minicla03.coinquylifek.AUTH.Data.Response.AuthResult
import minicla03.coinquylifek.AUTH.Domain.Model.User
import minicla03.coinquylifek.AUTH.Domain.Repository.IAuthRepository
import retrofit2.Call

class AuthRepository(application: Application) : IAuthRepository
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