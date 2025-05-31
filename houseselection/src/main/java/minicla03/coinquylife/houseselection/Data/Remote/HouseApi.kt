package minicla03.coinquylifek.HOUSE.Data.Remote

import com.coinquyteam.houseSelectionApplication.Data.CoinquyHouse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface HouseApi
{
    @POST("house/create")
    suspend fun createHouse(@Body house: CoinquyHouse): Response<HouseResult>

    @POST("house/loginHouse")
    suspend fun loginHouse(@Body house: CoinquyHouse): Response<HouseResult>
}
