package minicla03.coinquylifek.HOUSE.Domain.Repository

class ISelectHouseRepository
{
    suspend fun createHouse(houseName: String, address: String, token: String): HouseResult

    suspend fun joinHouse(houseCode: String, token: String): HouseResult
}
