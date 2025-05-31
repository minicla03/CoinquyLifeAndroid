package minicla03.coinquylife.houseselection.Domain.UseCase

import minicla03.coinquylife.houseselection.Data.Response.HouseStatus
import minicla03.coinquylifek.HOUSE.Data.Remote.HouseResult

interface INewHouseUseCase
{
    suspend fun createHouse(
        houseName: String,
        address: String,
        callback: (HouseResult?)->Unit)
}