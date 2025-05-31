package minicla03.coiquylife.authentication.Data.Remote

import com.coinquyteam.authApplication.Utility.AuthStatus
import minicla03.coinquylifek.APP.network.ApiService
import minicla03.coiquylife.authentication.data.remote.AuthApi
import minicla03.coiquylife.authentication.Data.Response.AuthResult
import minicla03.coiquylife.authentication.Domain.Model.User
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(
    apiService: ApiService
) {
    private val authApi: AuthApi = apiService.createService(
        AuthApi::class.java)

    suspend fun login(user: User): AuthResult {
        val response = authApi.login(user)
        return when (response.code())
        {
            200 ->{
                AuthResult(
                    statusAuth = AuthStatus.SUCCESS,
                    token =  response.body()?.token ?: ""
                )}
            404 ->{
                AuthResult(
                    statusAuth = AuthStatus.USER_NOT_FOUND,
                    token = response.body()?.token ?: ""
                )}
            401-> {
                AuthResult(
                    statusAuth = AuthStatus.UNAUTHORIZED,
                    token = response.body()?.token ?: ""
                )
            }
            500 -> {
                AuthResult(
                    statusAuth = AuthStatus.ERROR,
                    token = response.body()?.token ?: ""
                )

            }
            else -> {
                AuthResult(
                    statusAuth = AuthStatus.ERROR,
                    token = response.body()?.token ?: ""
                )
            }
        }
    }

    suspend fun register(user: User): AuthResult {
        val response = authApi.register(user)
        return when (response.code()) {
            200 -> {
                AuthResult(
                    statusAuth = AuthStatus.SUCCESS,
                    token = response.body()?.token ?: ""
                )
            }
            409 -> {
                AuthResult(
                    statusAuth = AuthStatus.USER_ALREADY_EXISTS,
                    token = response.body()?.token ?: ""
                )
            }
            401 -> {
                AuthResult(
                    statusAuth = AuthStatus.INVALID_EMAIL,
                    token = response.body()?.token ?: ""
                )
            }
            500 -> {
                AuthResult(
                    statusAuth = AuthStatus.ERROR,
                    token = response.body()?.token ?: ""
                )
            }
            else -> {
                AuthResult(
                    statusAuth = AuthStatus.ERROR,
                    token = response.body()?.token ?: ""
                )
            }
        }
    }

    suspend fun getUser(token: String?): String? {
        val response = authApi.verifyToken(Map.of("token", token))
        return response.body()?.keys("username") as? String ?: return nul
    }
}
