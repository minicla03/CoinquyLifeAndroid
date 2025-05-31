package minicla03.coiquylife.authentication.Domain.Usecase

import minicla03.coiquylife.authentication.Data.Response.AuthResult

interface ILoginUserUseCase
{
    suspend fun login(email: String, password: String, callback: (AuthResult) -> Unit)
}
