package minicla03.coiquylife.authentication.Domain.Model

data class User(
    var username: String,
    var password: String,
    var surname: String,
    var email: String,
    var profileImage: ByteArray? = null,
    var houseUser: String? = null,
    val usernameOrEmail: String
){

    constructor(username: String, password: String) : this(
        username = username,
        password = password,
        surname = "",
        email = "",
        profileImage = null,
        houseUser = null,
        usernameOrEmail = ""
    )
}