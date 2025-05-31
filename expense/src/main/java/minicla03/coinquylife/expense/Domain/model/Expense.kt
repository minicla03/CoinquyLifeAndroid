package minicla03.coinquylife.expense.Domain.model

data class Expense(
    val houseId: String,
    val description: String,
    val amount: Double,
    val date: String,
    val status: StatusExpense,
    val createdBy: String,
    val participants: List<String>
)