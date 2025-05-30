package minicla03.coiquylife.authentication.Domain.Model

import java.util.UUID

data class User(
    var id: String = UUID.randomUUID().toString(),
    var username: String?,
    var password: String?,
    var surname: String?,
    var email: String? ,
    var profileImage: ByteArray? = null,
    var houseUser: String? = null)