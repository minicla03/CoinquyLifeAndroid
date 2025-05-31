package minicla03.coinquylife.expense.Domain.UseCase

import com.coinquyteam.expense.Utility.ExpenseDebtResult
import minicla03.coinquylife.expense.Data.repositoryImp.ExpenseRepository
import minicla03.coinquylife.expense.Domain.model.Debt
import minicla03.coinquylife.expense.Domain.model.Expense
import minicla03.coinquylife.expense.Domain.repository.IExpenseRepository
import javax.inject.Inject

class CalculateDebtUseCase @Inject constructor(
    private val repository: IExpenseRepository
) {
    suspend operator fun invoke(houseId: String): Map<String, Debt>
    {
        return repository.calculateDebt(houseId)
    }
}
