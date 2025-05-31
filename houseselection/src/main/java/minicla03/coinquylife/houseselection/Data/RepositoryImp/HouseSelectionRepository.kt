package minicla03.coinquylife.houseselection.Data.RepositoryImp

import com.coinquyteam.houseSelectionApplication.Data.CoinquyHouse
import minicla03.coinquylife.houseselection.Data.Remote.HouseRemoteDataSource
import minicla03.coinquylife.houseselection.Domain.Repository.IHouseSelectionRepository
import minicla03.coinquylifek.HOUSE.Data.Remote.HouseResult
import javax.inject.Inject

class HouseSelectionRepository @Inject constructor(
    private val houseRemoteDataSource: HouseRemoteDataSource
) : IHouseSelectionRepository
{
    override suspend fun createHouse(house: CoinquyHouse): HouseResult? {
        return houseRemoteDataSource.createHouse(house)
    }

    override suspend fun loginHouse(house: CoinquyHouse): HouseResult? {
         return houseRemoteDataSource.joinHouse(house)
    }

}