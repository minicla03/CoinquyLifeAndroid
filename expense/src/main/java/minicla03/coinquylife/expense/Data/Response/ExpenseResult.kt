package minicla03.coinquylife.expense.Data.Response

data class ExpenseResult(
    val status: ExpenseStatus? = null,
    val message: String? = null,
    val expenses: Any? = null
)