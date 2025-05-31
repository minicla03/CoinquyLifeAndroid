package minicla03.coiquylife.authentication.Domain.Usecase

import com.coinquyteam.authApplication.Utility.AuthStatus
import minicla03.coinquylifek.APP.security.TokenManager
import minicla03.coiquylife.authentication.Domain.Model.User
import minicla03.coiquylife.authentication.domain.repository.IAuthRepository
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(
    private val repository: IAuthRepository,
    private val tokenManager: TokenManager
): ILoginUserUseCase{
    override suspend fun login(user: User, callback: (AuthStatus) -> Unit) {
        try {
            val response = repository.login(user)
            val result = response.body()

            when (result?.statusAuth) {
                AuthStatus.SUCCESS  -> {
                    result.token.let { tokenManager.saveToken(it) }
                    callback(AuthStatus.SUCCESS)
                }
                AuthStatus.USER_NOT_FOUND -> callback(AuthStatus.USER_NOT_FOUND)
                AuthStatus.INVALID_CREDENTIALS -> callback(AuthStatus.INVALID_CREDENTIALS)
                null -> callback(AuthStatus.ERROR("Nessuna risposta dal server"))
                else -> callback(AuthStatus.ERROR("Errore sconosciuto"))
            }
        } catch (e: Exception) {
            callback(AuthStatus.ERROR("Errore di rete o interno: ${e.localizedMessage ?: "sconosciuto"}"))
        }
    }
}