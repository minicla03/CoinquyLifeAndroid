package minicla03.coinquylife.houseselection.Domain.UseCase

import minicla03.coinquylifek.HOUSE.Data.Remote.HouseResult

interface IJoinHouseUseCase
{
    suspend fun joinHouse(houseId: String, callback: (HouseResult?) -> Unit)

}