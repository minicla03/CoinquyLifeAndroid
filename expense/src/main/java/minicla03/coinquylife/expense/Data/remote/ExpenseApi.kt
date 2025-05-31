package minicla03.coinquylife.expense.Data.remote

import com.coinquyteam.expense.Utility.ExpenseDebtResult
import minicla03.coinquylife.expense.Data.Response.ExpenseResult
import minicla03.coinquylife.expense.Domain.model.Expense
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ExpenseApi
{
    @POST("expense/createExpense")
    suspend fun createExpense(@Body expense: Expense): Response<ExpenseResult>

    @GET("expense/getAllExpenses")
    suspend fun getAllExpenses(@Query("houseId") houseId: String): Response<ExpenseResult>

    @POST("expense/calculateDebt")
    suspend fun calculateDebt(@Body body: Map<String, String>): Response<ExpenseDebtResult>

    @POST("expense/updateExpenseStatus")
    suspend fun updateExpenseStatus(@Body body: Map<String, String>): Response<ExpenseResult>
}
