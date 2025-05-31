package minicla03.coinquylife.expense.Domain.UseCase

import minicla03.coinquylife.expense.Data.Response.ExpenseResult
import minicla03.coinquylife.expense.Domain.model.Expense
import minicla03.coinquylife.expense.Domain.model.StatusExpense
import minicla03.coinquylife.expense.Domain.repository.IExpenseRepository
import javax.inject.Inject

class CreateExpenseUseCase @Inject constructor(
    private val repository: IExpenseRepository
) {
    suspend operator fun invoke(description: String, amount: Double, houseId: String): ExpenseResult? {
        if (amount <= 0) {
            throw IllegalArgumentException("Amount must be greater than zero")
        }

        val expense = Expense(
            description = description,
            amount = amount,
            date = System.currentTimeMillis().toString(),
            status = StatusExpense.PENDING,
            createdBy = "userId",
            houseId = houseId,
            participants = emptyList()
        )
        return repository.createExpense(expense)
    }
}