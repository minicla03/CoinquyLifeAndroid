package minicla03.coiquylife.authentication.Domain.Usecase

import minicla03.coinquylifek.APP.TokenManager
import retrofit2.Call
import minicla03.coiquylife.authentication.Data.Response.AuthResult
import minicla03.coiquylife.authentication.Data.Response.AuthStatus
import minicla03.coiquylife.authentication.Domain.Repository.IAuthRepository
import java.util.function.Consumer
import retrofit2.Callback
import retrofit2.Response

class LoginUserUseCase(repository: IAuthRepository, private val tokenManager: TokenManager) : ILoginUserUseCase
{
    private val repository: IAuthRepository = repository

    override suspend fun login(email: String?, password: String?, callback: Consumer<AuthResult?>)
    {
        val remoteCall: Call<AuthResult?>? = repository.login(email!!, password!!)

        remoteCall?.enqueue(object : Callback<AuthResult?>
        {
            override fun onResponse(call: Call<AuthResult?>, response: Response<AuthResult?>)
            {
                val authResult = response.body()
                if (response.isSuccessful && authResult != null)
                {
                    if (authResult.statusAuth == AuthStatus.SUCCESS)
                    {
                        val token = authResult.token
                        tokenManager.saveToken(token)
                        callback.accept(AuthResult(AuthStatus.SUCCESS, token))
                    }
                    else
                    {
                        callback.accept(AuthResult(AuthStatus.WRONG_PASSWORD, ""))
                    }
                } else {
                    callback.accept(AuthResult(AuthStatus.ERROR, ""))
                }
            }

            override fun onFailure(call: Call<AuthResult?>, t: Throwable) {
                t.printStackTrace()
                callback.accept(AuthResult(AuthStatus.ERROR, ""))
            }
        })
    }
}
