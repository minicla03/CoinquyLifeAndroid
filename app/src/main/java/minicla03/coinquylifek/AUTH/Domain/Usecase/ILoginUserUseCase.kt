package minicla03.coinquylifek.AUTH.Domain.Usecase

import minicla03.coinquylifek.AUTH.Data.Response.AuthResult
import java.util.function.Consumer

interface ILoginUserUseCase
{
    suspend fun login(email: String?, password: String?, callback: Consumer<AuthResult?>)
}
