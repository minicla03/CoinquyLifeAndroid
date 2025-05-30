package minicla03.coiquylife.authentication.Domain.Usecase

import minicla03.coiquylife.authentication.Data.Response.AuthResult
import minicla03.coiquylife.authentication.Domain.Model.User
import java.util.function.Consumer

interface IRegisterUserUseCase
{
    suspend fun register(user: User?, callback: Consumer<AuthResult?>)
}
