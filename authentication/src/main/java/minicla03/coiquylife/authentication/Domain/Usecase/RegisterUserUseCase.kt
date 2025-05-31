package minicla03.coiquylife.authentication.Domain.Usecase

import com.coinquyteam.authApplication.Utility.AuthStatus
import minicla03.coiquylife.authentication.Data.Response.AuthResult
import minicla03.coiquylife.authentication.Domain.Model.User
import minicla03.coiquylife.authentication.domain.repository.IAuthRepository
import java.util.regex.Pattern
import javax.inject.Inject


class RegisterUserUseCase @Inject constructor(
    private val repository: IAuthRepository
) : IRegisterUserUseCase {
    override suspend fun register(
        username: String,
        email: String,
        password: String,
        name: String,
        surname: String,
        callback: (AuthResult) -> Unit
    ) {
        if( isInvalidEmail(email)) {
            callback(AuthResult(AuthStatus.INVALID_EMAIL, "Email non valido"))
        }

        val user = User(
            username = username,
            password = password,
            surname = surname,
            email = email,
            profileImage = null,
            houseUser = null,
            usernameOrEmail = username
        )

        try
        {
            callback(repository.register(user))
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