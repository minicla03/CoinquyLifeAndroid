package minicla03.coiquylife.authentication.Data

import minicla03.coiquylife.authentication.Data.Response.AuthResult
import minicla03.coiquylife.authentication.Domain.Model.User

interface IDataSource
{
    suspend fun login(email: String, password: String): AuthResult

    suspend fun register(user: User): AuthResult
}