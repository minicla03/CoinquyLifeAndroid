package minicla03.coiquylife.authentication.Domain.Repository

import minicla03.coiquylife.authentication.Domain.Model.User
import minicla03.coiquylife.authentication.Data.Response.AuthResult

interface IAuthRepository
{
    suspend fun login(usernameOrEmail: String?, password: String?): AuthResult

    suspend fun register(user: User): AuthResult
}
