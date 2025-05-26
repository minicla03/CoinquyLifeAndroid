package minicla03.coinquylifek.HOUSE.Data.Remote

import com.coinquyteam.houseSelectionApplication.Data.CoinquyHouse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface HouseApi
{
    @POST("/house/create")
    fun createHouse(@Header("Authorization") authToken: String, @Body house: CoinquyHouse): Call<HouseResult?>

    @POST("/house/loginHouse")
    fun loginHouse(@Header("Authorization") authToken: String, @Body house: CoinquyHouse): Call<HouseResult?>
}
