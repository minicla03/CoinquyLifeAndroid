package minicla03.coiquylife.authentication.Domain.Usecase

import com.coinquyteam.authApplication.Utility.AuthStatus
import minicla03.coiquylife.authentication.Domain.Model.User

interface ILoginUserUseCase
{
    suspend fun login(user: User, callback: (AuthStatus) -> Unit)
}
