package minicla03.coinquylife.expense.Domain.UseCase

import minicla03.coinquylife.expense.Domain.model.Expense

interface IGetAllExpensesUseCase
{
    suspend operator fun invoke(houseId: String): List<Expense>?
}
