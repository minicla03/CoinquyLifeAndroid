package minicla03.coinquylife.houseselection.Data.Response

sealed class HouseStatus
{
    object INVALID_INPUT : HouseStatus()
    object ERROR : HouseStatus()
    object HOUSE_ALREADY_EXISTS : HouseStatus() {

    }

    object HOUSE_NOT_CREATED : HouseStatus() {

    }

    object LINKED_SUCCES : HouseStatus() {

    }

    object USER_NOT_FOUND : HouseStatus() {

    }

    object HOUSE_NOT_FOUND : HouseStatus() {

    }

    object USER_ALREADY_LINKED : HouseStatus() {

    }
}