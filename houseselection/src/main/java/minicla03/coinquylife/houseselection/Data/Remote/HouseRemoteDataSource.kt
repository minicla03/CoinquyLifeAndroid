package minicla03.coinquylife.houseselection.Data.Remote

import androidx.security.crypto.EncryptedSharedPreferences
import minicla03.coinquylifek.APP.ApiService
import minicla03.coinquylifek.APP.TokenManager
import minicla03.coinquylifek.HOUSE.Data.Remote.HouseResult
import minicla03.coinquylifek.HOUSE.Data.Response.HouseResult

class HouseRemoteDataSource
{
    private val houseApi : HouseApi = ApiService.createService(
        HouseApi::class.java,
        tokenProvider = { TokenManager.getToken()?: "" }
    )

    suspend fun createHouse(authToken: String, house: HouseResult): HouseResult?
    {
        return houseApi.createHouse(authToken, house).execute().body()
    }

    suspend fun joinHouse(authToken: String, house: HouseResult): HouseResult?
    {
        return houseApi.loginHouse(authToken, house).execute().body()
    }
}