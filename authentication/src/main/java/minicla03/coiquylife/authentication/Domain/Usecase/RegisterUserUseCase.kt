package minicla03.coiquylife.authentication.Domain.Usecase


import com.coinquyteam.authApplication.Utility.AuthStatus
import minicla03.coiquylife.authentication.Domain.Model.User
import minicla03.coiquylife.authentication.domain.repository.IAuthRepository
import javax.inject.Inject


class RegisterUserUseCase @Inject constructor(
    private val repository: IAuthRepository
) : IRegisterUserUseCase {

    override suspend fun register(user: User, callback: (AuthStatus) -> Unit) {
        try {
            val response = repository.register(user)
            val result = response.body()

            when (result?.statusAuth) {
                AuthStatus.SUCCESS -> callback(AuthStatus.SUCCESS)
                AuthStatus.USER_ALREADY_EXISTS -> callback(AuthStatus.USER_ALREADY_EXISTS)
                else -> callback(AuthStatus.ERROR("Errore sconosciuto"))
            }
        } catch (e: Exception) {
            callback(AuthStatus.ERROR("Errore: ${e.localizedMessage ?: "sconosciuto"}"))
        }
    }

}
