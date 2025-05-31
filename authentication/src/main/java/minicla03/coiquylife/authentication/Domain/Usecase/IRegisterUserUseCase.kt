package minicla03.coiquylife.authentication.Domain.Usecase

import com.coinquyteam.authApplication.Utility.AuthStatus
import minicla03.coiquylife.authentication.Domain.Model.User

interface IRegisterUserUseCase {
    suspend fun register(user: User, callback: (AuthStatus) -> Unit)
}
