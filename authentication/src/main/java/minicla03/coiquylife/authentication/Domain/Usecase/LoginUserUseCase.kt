package minicla03.coiquylife.authentication.Domain.Usecase

import com.coinquyteam.authApplication.Utility.AuthStatus
import minicla03.coiquylife.authentication.Data.Response.AuthResult
import minicla03.coiquylife.authentication.Domain.Model.User
import minicla03.coiquylife.authentication.domain.repository.IAuthRepository
import java.util.regex.Pattern
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(
    private val repository: IAuthRepository,
): ILoginUserUseCase{
    override suspend fun login(email: String, password: String, callback: (AuthResult) -> Unit)
    {
        if (isInvalidEmail(email)) {
            callback(AuthResult(AuthStatus.INVALID_EMAIL, "Email non valido"))
        }

        val user = User(email,password)
        try
        {
            callback(repository.login(user))
        }
        catch (e: Exception) {
            callback(AuthResult(AuthStatus.ERROR, e.localizedMessage ?: "sconosciuto"))
        }
    }

    private fun isInvalidEmail(email: String?): Boolean {
        if (email == null) return true
        val emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
        return !Pattern.matches(emailRegex, email)
    }
}