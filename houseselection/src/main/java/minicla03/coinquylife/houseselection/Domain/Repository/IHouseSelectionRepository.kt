package minicla03.coinquylife.houseselection.Domain.Repository

import com.coinquyteam.houseSelectionApplication.Data.CoinquyHouse
import minicla03.coinquylifek.HOUSE.Data.Remote.HouseResult

interface IHouseSelectionRepository {
    suspend fun createHouse(house: CoinquyHouse): HouseResult?
    suspend fun loginHouse(house: CoinquyHouse): HouseResult?
}