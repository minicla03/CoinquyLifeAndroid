package minicla03.coinquylife.houseselection.Data.Remote

import com.coinquyteam.houseSelectionApplication.Data.CoinquyHouse
import minicla03.coinquylife.houseselection.Data.Response.HouseStatus
import minicla03.coinquylifek.APP.network.ApiService
import minicla03.coinquylifek.HOUSE.Data.Remote.HouseApi
import minicla03.coinquylifek.HOUSE.Data.Remote.HouseResult
import javax.inject.Inject

class HouseRemoteDataSource @Inject constructor(
    apiService: ApiService
) {
    private val houseApi: HouseApi = apiService.createService(
        HouseApi::class.java)

    suspend fun createHouse(house: CoinquyHouse): HouseResult? {
        val response = houseApi.createHouse(house)
        when(response.code())
        {
            201 -> { // HOUSE_CREATED
                val body = response.body()
                return body
            }
            409 -> { // HOUSE_ALREADY_EXISTS
                return HouseResult(
                    houseStatus = HouseStatus.HOUSE_ALREADY_EXISTS,
                    message = "La casa con questo nome esiste già"
                )
            }
            500 -> { // HOUSE_NOT_CREATED o errore generico
                return HouseResult(
                    houseStatus = HouseStatus.HOUSE_NOT_CREATED,
                    message = "Errore: ${response.message()}"
                )
            }
            else -> {
                return HouseResult(
                    houseStatus = HouseStatus.HOUSE_NOT_CREATED,
                    message = "Server error"
                )
            }
        }
    }

    suspend fun joinHouse(house: CoinquyHouse): HouseResult? {
        val response = houseApi.loginHouse(house)
        return when (response.code()) {
            200 -> HouseResult(
                houseStatus = HouseStatus.LINKED_SUCCES,
                message = "House linked successfully"
            )
            404 -> {
                val errorMsg = response.errorBody()?.string() ?: ""
                if (errorMsg.contains("User not found")) {
                    HouseResult(
                        houseStatus = HouseStatus.USER_NOT_FOUND,
                        message = "User not found"
                    )
                } else {
                    HouseResult(
                        houseStatus = HouseStatus.HOUSE_NOT_FOUND,
                        message = "House not found"
                    )
                }
            }
            409 -> HouseResult(
                houseStatus = HouseStatus.USER_ALREADY_LINKED,
                message = "User already linked to a house"
            )
            500 -> HouseResult(
                houseStatus = HouseStatus.HOUSE_NOT_CREATED,
                message = "Error linking house to user"
            )
            else -> HouseResult(
                houseStatus = HouseStatus.HOUSE_NOT_CREATED,
                message = "Server error"
            )
        }
    }
}