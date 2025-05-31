package minicla03.coiquylife.authentication.domain.repository

import minicla03.coiquylife.authentication.Data.Response.AuthResult
import minicla03.coiquylife.authentication.Domain.Model.User

interface IAuthRepository
{
    suspend fun login(user: User): AuthResult
    suspend fun register(user: User): AuthResult
}
