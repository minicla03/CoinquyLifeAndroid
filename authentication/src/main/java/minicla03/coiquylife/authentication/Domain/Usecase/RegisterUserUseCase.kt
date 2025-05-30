package minicla03.coiquylife.authentication.Domain.Usecase

import retrofit2.Call
import minicla03.coiquylife.authentication.Data.Response.AuthResult
import minicla03.coiquylife.authentication.Data.Response.AuthStatus
import minicla03.coiquylife.authentication.Domain.Model.User
import minicla03.coiquylife.authentication.Domain.Repository.IAuthRepository
import java.util.function.Consumer
import retrofit2.Callback
import retrofit2.Response

class RegisterUserUseCase(
    private val repository: IAuthRepository,
) : IRegisterUserUseCase {

    override suspend fun register(user: User?, callback: Consumer<AuthResult?>)
    {
        if (user == null)
        {
            callback.accept(AuthResult(AuthStatus.ERROR, "User object is null"))
            return
        }

        val remoteCall: Call<AuthResult?>? = repository.register(user)

        remoteCall?.enqueue(object : Callback<AuthResult?>
        {
            override fun onResponse(call: Call<AuthResult?>, response: Response<AuthResult?>)
            {
                if (response.isSuccessful && response.body() != null)
                {
                    callback.accept(AuthResult(AuthStatus.SUCCESS, "Registered successfully!"))
                }
                else
                {
                    when (response.code())
                    {
                        409 -> callback.accept(AuthResult(AuthStatus.USER_ALREADY_EXISTS, "User already exists"))
                        401 -> callback.accept(AuthResult(AuthStatus.INVALID_EMAIL, "Invalid email format"))
                        else -> callback.accept(AuthResult(AuthStatus.ERROR, "Registration failed"))
                    }
                }
            }

            override fun onFailure(call: Call<AuthResult?>, t: Throwable)
            {
                callback.accept(AuthResult(AuthStatus.ERROR, t.message ?: "Unknown error"))
            }
        })
    }
}
