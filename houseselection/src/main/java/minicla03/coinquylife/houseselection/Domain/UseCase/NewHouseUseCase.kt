package minicla03.coinquylife.houseselection.Domain.UseCase

import com.coinquyteam.houseSelectionApplication.Data.CoinquyHouse
import minicla03.coinquylife.houseselection.Domain.Repository.IHouseSelectionRepository
import minicla03.coinquylifek.APP.AppPreferences
import minicla03.coinquylifek.HOUSE.Data.Remote.HouseResult
import javax.inject.Inject

class NewHouseUseCase @Inject constructor(
    private val repository: IHouseSelectionRepository,
    private val appPreferences: AppPreferences
) : INewHouseUseCase {
    override suspend fun createHouse(
        houseName: String,
        address: String,
        callback: (HouseResult?) -> Unit
    ) {
        val house = CoinquyHouse(houseName, address)
        repository.createHouse(house)?.let { result ->
            result.houseId?.let { appPreferences.save("houseId", it) }
            callback(result)
        }
    }
}