package minicla03.coinquylife.expense.Domain.UseCase

import minicla03.coinquylife.expense.Data.Response.ExpenseResult
import minicla03.coinquylife.expense.Domain.repository.IExpenseRepository
import javax.inject.Inject

class UpdateExpenseStatusUseCase @Inject constructor(
    private val repository: IExpenseRepository
) {
    suspend operator fun invoke(expenseId: String): ExpenseResult?
    {
        return repository.updateExpenseStatus(expenseId)
    }
}
