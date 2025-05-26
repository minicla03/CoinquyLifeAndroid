package minicla03.coinquylifek.AUTH.Data.Response

enum class AuthStatus
{
    SUCCESS,
    USER_NOT_FOUND,
    WRONG_PASSWORD,
    ERROR,
    ALREADY_REGISTERED,
    INVALID_EMAIL,
    INVALID_PASSWORD,
    USER_ALREADY_EXISTS,
}