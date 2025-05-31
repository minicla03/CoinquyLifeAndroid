package minicla03.coiquylife.authentication.Data.Response

import com.coinquyteam.authApplication.Utility.AuthStatus

data class AuthResult(
    var statusAuth: AuthStatus,
    var token: String
)
