package minicla03.coiquylife.authentication.Data.Remote

import minicla03.coinquylifek.APP.network.ApiService
import minicla03.coiquylife.authentication.data.remote.AuthApi
import minicla03.coiquylife.authentication.Data.Response.AuthResult
import minicla03.coiquylife.authentication.Domain.Model.User
import retrofit2.Response
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(
    apiService: ApiService
) {
    private val authApi: AuthApi = apiService.createService(
        AuthApi::class.java)

    suspend fun login(user: User): Response<AuthResult> {
        return authApi.login(user)
    }

    suspend fun register(user: User): Response<AuthResult> {
        return authApi.register(user)
    }
}
