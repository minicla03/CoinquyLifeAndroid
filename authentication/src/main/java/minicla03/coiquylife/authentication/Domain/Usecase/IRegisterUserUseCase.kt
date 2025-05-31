package minicla03.coiquylife.authentication.Domain.Usecase

import minicla03.coiquylife.authentication.Data.Response.AuthResult

interface IRegisterUserUseCase
{
    suspend fun register(username: String, email: String, password: String, name: String, surname: String, callback: (AuthResult) -> Unit)
}
