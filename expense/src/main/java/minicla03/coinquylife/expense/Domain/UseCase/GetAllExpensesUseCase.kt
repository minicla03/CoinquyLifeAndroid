package minicla03.coinquylife.expense.Domain.UseCase

import minicla03.coinquylife.expense.Domain.model.Expense
import minicla03.coinquylife.expense.Domain.repository.IExpenseRepository
import javax.inject.Inject

class GetAllExpensesUseCase @Inject constructor(
    private val repository: IExpenseRepository
) : IGetAllExpensesUseCase{
    override suspend operator fun invoke(houseId: String): List<Expense>?
    {
        return repository.getAllExpenses(houseId)
    }
}
