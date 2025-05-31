package minicla03.coinquylife.expense.Domain.UseCase

import minicla03.coinquylife.expense.Data.Response.ExpenseResult
import minicla03.coinquylife.expense.Domain.model.Expense
import minicla03.coinquylife.expense.Domain.model.StatusExpense
import minicla03.coinquylife.expense.Domain.repository.IExpenseRepository
import java.util.Date
import javax.inject.Inject

class CreateExpenseUseCase @Inject constructor(
    private val repository: IExpenseRepository
    private val app: AppPreferences
) : ICreateExpenseUseCase {

    override suspend operator fun invoke(description: String,
                                         amount: Double, houseId: String,
                                         data: Date?, participants: List<String>): ExpenseResult? {

        if (amount <= 0) {
            throw IllegalArgumentException("Amount must be greater than zero")
        }

        val expense = Expense(
            description = description,
            amount = amount,
            date = data,
            status = StatusExpense.PENDING,
            createdBy = app.getString("userId"),
            houseId = houseId,
            participants = participants
        )
        return repository.createExpense(expense)
    }
}