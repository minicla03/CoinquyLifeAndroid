package minicla03.coinquylife.expense.Domain.model

data class Debt(
    var createdBy: String?,
    var partecipants: Map<String, Double> //chi deve e quanto
)
