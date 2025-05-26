package minicla03.coinquylifek.AUTH.Domain.Repository

import minicla03.coinquylifek.AUTH.Data.Response.AuthResult
import minicla03.coinquylifek.AUTH.Domain.Model.User
import retrofit2.Call

interface IAuthRepository
{
    suspend fun login(usernameOrEmail: String, password: String): Call<AuthResult?>?

    suspend fun register(user: User): Call<AuthResult?>?
}
