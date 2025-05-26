package minicla03.coinquylifek.AUTH.Domain.Usecase

import minicla03.coinquylife.Auth.DOMAIN.Repository.IAuthRepository
import java.util.concurrent.Executor
import java.util.function.Consumer

class RegisterUserUseCase(
    repository: IAuthRepository,
    private val executor: Executor
) :
    IRegisterUserUseCase {
    private val repository: IAuthRepository = repository

    override fun register(user: User?, callback: Consumer<AuthResult?>) {
        val remoteCall: Call<AuthResult> = repository.registerRemote(user)

        remoteCall.enqueue(object : Callback() {
            override fun onResponse(call: Call<AuthResult?>?, response: Response<AuthResult?>) {
                if (response.isSuccessful()) {
                    executor.execute {
                        try {
                            repository.insertUser(user)
                            callback.accept(
                                AuthResult(
                                    AuthStatus.SUCCESS,
                                    "Registered successfully!"
                                )
                            )
                        } catch (sqle: SQLiteConstraintException) {
                            callback.accept(
                                AuthResult(
                                    AuthStatus.USER_ALREADY_EXISTS,
                                    sqle.message
                                )
                            )
                        } catch (e: Exception) {
                            e.printStackTrace()
                            callback.accept(AuthResult(AuthStatus.ERROR, null))
                        }
                    }
                } else if (response.code() === 409) {
                    callback.accept(AuthResult(AuthStatus.USER_ALREADY_EXISTS, null))
                } else if (response.code() === 401) {
                    callback.accept(AuthResult(AuthStatus.INVALID_EMAIL, null))
                } else {
                    callback.accept(AuthResult(AuthStatus.ERROR, null))
                }
            }

            override fun onFailure(
                call: Call<minicla03.coinquylife.DATALAYER.remote.AuthAPI.AuthResult?>?,
                t: Throwable?
            ) {
                //t.printStackTrace();
                callback.accept(AuthResult(AuthStatus.ERROR, null))
            }
        })
    }
}
