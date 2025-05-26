package minicla03.coinquylifek.AUTH.Domain.Usecase

import minicla03.coinquylifek.DATALAYER.local.entity.User

interface IRegisterUserUseCase
{
    fun register(user: User?, callback: java.util.function.Consumer<AuthResult?>)
}
