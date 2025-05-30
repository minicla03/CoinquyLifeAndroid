package minicla03.coiquylife.authentication.Domain.Repository

import minicla03.coiquylife.authentication.Domain.Model.User
import minicla03.coiquylife.authentication.Data.Response.AuthResult
import retrofit2.Call

interface IAuthRepository
{
    suspend fun login(usernameOrEmail: String, password: String): Call<AuthResult?>?

    suspend fun register(user: User): Call<AuthResult?>?
}
