package minicla03.coiquylife.authentication.domain.repository

import minicla03.coiquylife.authentication.Data.Response.AuthResult
import minicla03.coiquylife.authentication.Domain.Model.User
import retrofit2.Response

interface IAuthRepository
{
    suspend fun login(user: User): Response<AuthResult>
    suspend fun register(user: User): Response<AuthResult>
}
