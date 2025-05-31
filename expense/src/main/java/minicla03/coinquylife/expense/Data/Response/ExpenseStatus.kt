package minicla03.coinquylife.expense.Data.Response

enum class ExpenseStatus
{
    SUCCESS("Success"),
    ERROR("Error"),
    NOT_FOUND("Not Found"),
    INVALID_INPUT("Invalid Input"),
    UNAUTHORIZED("Unauthorized"),
    FORBIDDEN("Forbidden"),
    NO_CONTENT("No Content");

    private val status: String

    constructor(status: String) {
        this.status = status
    }

    fun getStatus(): String {
        return status
    }
}