package minicla03.coinquylife.houseselection.Domain.UseCase

import com.coinquyteam.houseSelectionApplication.Data.CoinquyHouse
import minicla03.coinquylife.houseselection.Domain.Repository.IHouseSelectionRepository
import minicla03.coinquylifek.HOUSE.Data.Remote.HouseResult
import javax.inject.Inject

class NewHouseUseCase @Inject constructor(
    private val repository: IHouseSelectionRepository,
) : INewHouseUseCase {
    override suspend fun createHouse(
        houseName: String,
        address: String,
        callback: (HouseResult?) -> Unit
    ) {
        val house = CoinquyHouse(houseName, address)
        callback(repository.createHouse(house))
    }
}