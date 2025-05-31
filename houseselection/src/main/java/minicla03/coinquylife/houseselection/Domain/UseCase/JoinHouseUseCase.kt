package minicla03.coinquylife.houseselection.Domain.UseCase

import com.coinquyteam.houseSelectionApplication.Data.CoinquyHouse
import minicla03.coinquylife.houseselection.Data.Response.HouseStatus
import minicla03.coinquylife.houseselection.Data.Response.HouseStatus.*
import minicla03.coinquylife.houseselection.Domain.Repository.IHouseSelectionRepository
import minicla03.coinquylifek.HOUSE.Data.Remote.HouseResult
import javax.inject.Inject

class JoinHouseUseCase @Inject constructor(
    private val repository: IHouseSelectionRepository
) : IJoinHouseUseCase{
    override suspend fun joinHouse(
        houseId: String,
        callback: (HouseResult?) -> Unit
    ) {
        val coinquyHouse = CoinquyHouse(houseId = houseId)
        callback(repository.loginHouse(coinquyHouse))
    }

}

