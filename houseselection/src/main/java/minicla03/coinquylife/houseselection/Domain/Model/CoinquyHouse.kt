package com.coinquyteam.houseSelectionApplication.Data

data class CoinquyHouse(
    val houseName: String = "",
    val houseAddress: String = "",
    val houseId: String? = null
) {

    constructor(houseName: String, houseAddress: String) : this(houseName, houseAddress, null)
    constructor(houseId: String) : this("", "", houseId)
}
