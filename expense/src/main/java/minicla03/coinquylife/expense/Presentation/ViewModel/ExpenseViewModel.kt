package minicla03.coinquylife.expense.Presentation.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import minicla03.coinquylife.expense.Domain.UseCase.CreateExpenseUseCase
import minicla03.coinquylife.expense.Domain.UseCase.GetAllExpensesUseCase
import minicla03.coinquylife.expense.Domain.UseCase.CalculateDebtUseCase
import minicla03.coinquylife.expense.Domain.UseCase.UpdateExpenseStatusUseCase
import minicla03.coinquylife.expense.Data.Response.ExpenseResult
import minicla03.coinquylife.expense.Domain.model.Expense
import kotlinx.coroutines.launch
import minicla03.coinquylife.expense.Data.Response.ExpenseStatus
import minicla03.coinquylife.expense.Domain.UseCase.ICreateExpenseUseCase
import minicla03.coinquylife.expense.Domain.UseCase.IGetAllExpensesUseCase
import java.util.Date

@HiltViewModel
class ExpenseViewModel @Inject constructor(
    private val createExpenseUseCase: ICreateExpenseUseCase,
    private val getAllExpensesUseCase: IGetAllExpensesUseCase,
    private val calculateDebtUseCase: CalculateDebtUseCase,
    private val updateExpenseStatusUseCase: UpdateExpenseStatusUseCase
) : ViewModel() {

    private val _expenseState = MutableLiveData<ExpenseResult>()
    val expenseState: LiveData<ExpenseResult> = _expenseState

    private val _expensesState = MutableLiveData<List<Expense>?>()
    val expensesState: LiveData<List<Expense>> = _expensesState

    private val _debtState = MutableLiveData<ExpenseResult>()
    val debtState: LiveData<ExpenseResult> = _debtState

    private val _expenseUpdateState = MutableLiveData<ExpenseResult>()
    val expenseUpdateState: LiveData<ExpenseResult> = _expenseUpdateState

    fun createExpense(description: String,
                      amount: Double,
                      data: Date?,
                      participants: List<String>,
                      houseId: String)
    {
        if( description.isBlank() || amount.isNaN() || participants.isEmpty())
        {
            _expenseState.value = ExpenseResult(ExpenseStatus.EMPTY_INPUT,"Invalid input data")
            return
        }

        viewModelScope.launch {
            viewModelScope.launch {
                val result = createExpenseUseCase(description, amount, houseId, data, participants) { result ->
                    _expenseState.postValue(result)
                }
            }
        }
    }

    fun getAllExpenses(houseId: String)
    {
        viewModelScope.launch {
            val result = getAllExpensesUseCase(houseId)
            _expensesState.postValue(result)
            }
        }
    }

    fun calculateDebt(houseId: String)
    {
        viewModelScope.launch {
            val result = calculateDebtUseCase(houseId){result->
                debtState.postValue(result)
            }
        }
    }

    fun updateExpenseStatus(expense: Expense)
    {
        viewModelScope.launch {
            val result = updateExpenseStatusUseCase(expense) { result ->
                _expenseUpdateState.postValue(result)
            }
            if (result is ExpenseResult.Error) {
                _expenseUpdateState.postValue(result)
            }
        }
    }
}
