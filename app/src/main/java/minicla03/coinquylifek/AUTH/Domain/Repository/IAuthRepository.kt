package minicla03.coinquylifek.AUTH.Domain.Repository

interface IAuthRepository
{
    suspend fun login(email: String, password: String): Result<UserSession>
    suspend fun register(email: String, password: String, username: String): Result<UserSession>
}
