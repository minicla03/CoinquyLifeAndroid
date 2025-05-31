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

@HiltViewModel
class ExpenseViewModel @Inject constructor(
    private val createExpenseUseCase: CreateExpenseUseCase,
    private val getAllExpensesUseCase: GetAllExpensesUseCase,
    private val calculateDebtUseCase: CalculateDebtUseCase,
    private val updateExpenseStatusUseCase: UpdateExpenseStatusUseCase
) : ViewModel() {

    private val _expenseState = MutableLiveData<ExpenseResult>()
    val expenseState: LiveData<ExpenseResult> = _expenseState

    private val _expensesState = MutableLiveData<ExpenseResult>()
    val expensesState: LiveData<ExpenseResult> = _expensesState

    private val _debtState = MutableLiveData<ExpenseResult>()
    val debtState= LiveData<ExpenseResult>() = _debtState

    private val _expenseUpdateState = MutableLiveData<ExpenseResult>()
    val expenseUpdateState: LiveData<ExpenseResult> = _expenseUpdateState

    fun createExpense(expense: Expense)
    {
        viewModelScope.launch {
            val result = createExpenseUseCase(expense)
            _expenseState.value = result
        }
    }

    fun getAllExpenses(houseId: String)
    {
        viewModelScope.launch {
            val result = getAllExpensesUseCase(houseId){
                _
            }
        }
    }

    fun calculateDebt(houseId: String)
    {
        viewModelScope.launch {
            val result = calculateDebtUseCase(houseId){ result->
                debtState.postValue(result)
            }
        }
    }
}
