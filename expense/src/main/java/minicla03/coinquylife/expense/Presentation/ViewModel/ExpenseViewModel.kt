package minicla03.coinquylife.expense.Presentation.ViewModel

@HiltViewModel
class ExpenseViewModel @Inject constructor(
    private val createExpenseUseCase: CreateExpenseUseCase,
    private val getAllExpensesUseCase: GetAllExpensesUseCase,
    private val calculateDebtUseCase: CalculateDebtUseCase,
    private val updateExpenseStatusUseCase: UpdateExpenseStatusUseCase
) : ViewModel() {

    private val _expenseState = MutableLiveData<ExpenseResult>()
    val expenseState: LiveData<ExpenseResult> = _expenseState


    fun createExpense(expense: Expense) {
        viewModelScope.launch {
            val result = createExpenseUseCase(expense)
            _expenseState.value = result
        }
    }

    // altri metodi analoghi...
}
