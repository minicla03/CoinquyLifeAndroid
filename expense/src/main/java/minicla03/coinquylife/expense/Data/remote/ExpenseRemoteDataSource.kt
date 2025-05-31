package minicla03.coinquylife.expense.Data.remote

import minicla03.coinquylife.expense.Domain.model.Expense
import minicla03.coinquylifek.APP.network.ApiService
import javax.inject.Inject

class ExpenseRemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {
    private val expenseApi: ExpenseApi = apiService.createService(ExpenseApi::class.java)

    suspend fun createExpense(expense: Expense) = expenseApi.createExpense(expense).body()

    suspend fun getAllExpenses(houseId: String) = expenseApi.getAllExpenses(houseId).body()

    suspend fun calculateDebt(body: Map<String, String>) = expenseApi.calculateDebt(body).body()

    suspend fun updateExpenseStatus(body: String) = expenseApi.updateExpenseStatus(body).body()

    suspend fun getUser(token: String?) = expenseApi.getUser(token).body()

}