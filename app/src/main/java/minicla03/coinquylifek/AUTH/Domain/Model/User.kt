package minicla03.coinquylifek.AUTH.Domain.Model

import java.util.UUID

data class User(
    var id: String = UUID.randomUUID().toString(),
    var username: String?,
    var password: String?,
    var surname: String?,
    var email: String? ,
    var level: Int = 0,
    var total_points: Int = 0,
    var language: String? = "ita",
    var profileImage: ByteArray? = null,
    var houseUser: String? = null)