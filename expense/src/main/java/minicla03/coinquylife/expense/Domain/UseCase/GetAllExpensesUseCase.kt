package minicla03.coinquylife.expense.Domain.UseCase

import retrofit2.Response

class GetAllExpensesUseCase @Inject constructor(
    private val repository: ExpenseRepository
) {
    suspend operator fun invoke(houseId: String): ExpenseResult
    {
        val body = repository.getAllExpenses(houseId)
        return if (response.isSuccessful)
        {

        } else {
            ExpenseResult.Error("Error fetching expenses: ${response.message()}")
        }
    }
}
