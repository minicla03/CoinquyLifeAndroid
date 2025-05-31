package minicla03.coinquylife.expense.Domain.UseCase

import minicla03.coinquylife.expense.Data.Response.ExpenseResult
import java.util.Date

interface ICreateExpenseUseCase
{
    suspend operator fun invoke(
        description: String,
        amount: Double,
        houseId: String,
        date: Date?,
        participants: List<String>
    ): ExpenseResult?
}
