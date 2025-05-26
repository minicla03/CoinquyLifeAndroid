package minicla03.coinquylifek.AUTH.Domain.Usecase

import minicla03.coinquylifek.DATALAYER.remote.AuthAPI.AuthResult

interface ILoginUserUseCase
{
    fun login(email: String?, password: String?, callback: java.util.function.Consumer<AuthResult?>)
}
