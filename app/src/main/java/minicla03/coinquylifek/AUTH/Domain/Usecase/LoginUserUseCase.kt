package minicla03.coinquylifek.AUTH.Domain.Usecase

import minicla03.coinquylifek.AUTH.Domain.Repository.IAuthRepository
import java.util.concurrent.Executor
import java.util.function.Consumer

class LoginUserUseCase(
    repository: IAuthRepository,
    private val executor: Executor
) : ILoginUserUseCase
{
    private val repository: IAuthRepository = repository

    override fun login(email: String?, password: String?, callback: Consumer<AuthResult?>)
    {
        val remoteCall: Call<AuthResult> = repository.getUserByEmailRemote(email, password)

        remoteCall.enqueue(object : Callback()
        {
            override fun onResponse(call: Call<AuthResult?>?, response: Response<AuthResult?>)
            {
                if (response.isSuccessful() && response.body() != null)
                {
                    val authResult: Unit = response.body()

                    if (authResult.getStatusAuth() === AuthStatus.SUCCESS)
                    {
                        val token: String = authResult.getToken()
                        CoinquyLife.getTokenManager().saveToken(token)
                        callback.accept(AuthResult(AuthStatus.SUCCESS, token))
                    }
                    else
                    {
                        callback.accept(AuthResult(AuthStatus.WRONG_PASSWORD, null))
                    }
                }
                else
                {
                    callback.accept(AuthResult(AuthStatus.ERROR, null))
                }
            }

            override fun onFailure(
                call: Call<minicla03.coinquylife.DATALAYER.remote.AuthAPI.AuthResult?>?,
                t: Throwable
            ) {
                t.printStackTrace()
                callback.accept(AuthResult(AuthStatus.ERROR, null))
            }
        })
    }
}
