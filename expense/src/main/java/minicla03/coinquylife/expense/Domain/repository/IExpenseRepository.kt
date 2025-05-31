package minicla03.coinquylife.expense.Domain.repository

import minicla03.coinquylife.expense.Data.Response.ExpenseResult
import minicla03.coinquylife.expense.Domain.model.Expense

interface IExpenseRepository
{
    suspend fun createExpense(expense: Expense): ExpenseResult?

    suspend fun getAllExpenses(houseId: String): List<Expense>?

    suspend fun calculateDebt(body: Map<String, String>): Any?

    suspend fun updateExpenseStatus(body: String): ExpenseResult?
}
