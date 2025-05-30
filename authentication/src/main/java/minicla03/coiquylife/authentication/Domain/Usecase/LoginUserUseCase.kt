package minicla03.coiquylife.authentication.Domain.Usecase

import minicla03.coinquylifek.APP.TokenManager
import minicla03.coiquylife.authentication.Data.Response.AuthResult
import minicla03.coiquylife.authentication.Data.Response.AuthStatus
import minicla03.coiquylife.authentication.Domain.Repository.IAuthRepository
import java.util.function.Consumer

class LoginUserUseCase(
    private val repository: IAuthRepository,
    private val tokenManager: TokenManager
) : ILoginUserUseCase
{
    override suspend fun login(email: String?, password: String?, callback: Consumer<AuthResult?>)
    {
        val result = repository.login(email, password)

        return if(result.statusAuth == AuthStatus.SUCCESS)
        {
            tokenManager.saveToken(result.token)
            callback.accept(result)
        } else {
            callback.accept(null)
        }
    }
}

