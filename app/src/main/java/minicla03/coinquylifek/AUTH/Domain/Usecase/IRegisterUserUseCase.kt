package minicla03.coinquylifek.AUTH.Domain.Usecase

import minicla03.coinquylifek.AUTH.Data.Response.AuthResult
import minicla03.coinquylifek.AUTH.Domain.Model.User
import java.util.function.Consumer

interface IRegisterUserUseCase
{
    suspend fun register(user: User?, callback: Consumer<AuthResult?>)
}
