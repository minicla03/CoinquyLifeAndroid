package minicla03.coinquylife.expense.Domain.model

enum class StatusExpense
{
    PENDING,
    APPROVED,
    REJECTED,
    PAID,
    CANCELLED;

    companion object {
        fun fromString(value: String): StatusExpense? {
            return StatusExpense.entries.find { it.name.equals(value, ignoreCase = true) }
        }
    }
}