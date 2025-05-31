package minicla03.coinquylife.expense.Data.repositoryImp

import minicla03.coinquylife.expense.Data.remote.ExpenseRemoteDataSource
import minicla03.coinquylife.expense.Domain.model.Expense
import minicla03.coinquylife.expense.Domain.repository.IExpenseRepository
import javax.inject.Inject

class ExpenseRepository @Inject constructor(
    private val expenseRemoteDataSource: ExpenseRemoteDataSource
) : IExpenseRepository
{
    override suspend fun createExpense(expense: Expense) = expenseRemoteDataSource.createExpense(expense)

    override suspend fun getAllExpenses(houseId: String) = expenseRemoteDataSource.getAllExpenses(houseId)?.expenses

    override suspend fun calculateDebt(body: Map<String, String>) = expenseRemoteDataSource.calculateDebt(body)?.debts

    override suspend fun updateExpenseStatus(body: String) = expenseRemoteDataSource.updateExpenseStatus(body)
}