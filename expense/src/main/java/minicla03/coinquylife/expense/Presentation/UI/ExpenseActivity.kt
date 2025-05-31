package minicla03.coinquylife.expense.UI

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import minicla03.coinquylife.expense.R
import minicla03.coinquylife.expense.Data.Response.ExpenseResult
import minicla03.coinquylife.expense.Domain.model.Expense
import minicla03.coinquylife.expense.Domain.model.StatusExpense
import minicla03.coinquylife.expense.Presentation.UI.DebtsDialogFragment
import minicla03.coinquylife.expense.Presentation.ViewModel.ExpenseViewModel
import minicla03.coinquylife.expense.ui.AddExpenseDialogFragment
import java.text.SimpleDateFormat
import java.util.*

import minicla03.coinquylife.expense.ui.ExpenseAdapter

@AndroidEntryPoint
class ExpenseActivity : AppCompatActivity()
{

    private lateinit var tvBalanceSummary: TextView
    private lateinit var rvExpenses: RecyclerView
    private lateinit var btnAddExpense: Button
    private lateinit var btnDebts: Button

    private lateinit var expenseAdapter: ExpenseAdapter

    private val expenseViewModel: ExpenseViewModel by viewModels()

    @Inject appPreference: AppPreference
    private val houseId by lazy {
        appPreference.getString("houseId")
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.expense_layout)

        tvBalanceSummary = findViewById(R.id.tv_balance_summary)
        rvExpenses = findViewById(R.id.rv_expenses)
        btnAddExpense = findViewById(R.id.btn_add_expense)
        btnDebts = findViewById(R.id.btn_debts)

        expenseAdapter = ExpenseAdapter(mutableListOf()) { expense ->
            expenseViewModel.updateExpenseStatus(expense.copy(status = StatusExpense.PAID))
        }
        rvExpenses.layoutManager = LinearLayoutManager(this)
        rvExpenses.adapter = expenseAdapter

        expenseViewModel.expensesState.observe(this) { expenses ->
            expenseAdapter.updateExpenses(expenses)
            updateBalanceSummary(expenses)
        }

        expenseViewModel.expenseUpdateState.observe(this) { result ->
            if (result is ExpenseResult.Success) {
                expenseViewModel.getAllExpenses(houseId)
            }
        }
        expenseViewModel.getAllExpenses(houseId)

        btnAddExpense.setOnClickListener {
            AddExpenseDialogFragment { description, amount, date , partecipants->
                val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                expenseViewModel.createExpense(description, amount, formatter.parse(date), partecipants, houseId)
                expenseViewModel.getAllExpenses(houseId)
            }.show(supportFragmentManager, "AddExpenseDialog")
        }

        btnDebts.setOnClickListener {
            expenseViewModel.calculateDebt(houseId)
            expenseViewModel.debtState.observe(this) { result ->
                if (result is ExpenseResult.Success) {
                    val debtSummary = buildDebtSummary(result.data)
                    DebtsDialogFragment(debtSummary).show(supportFragmentManager, "DebtsDialog")
                }
            }
        }
    }

    private fun updateBalanceSummary(expenses: ExpenseResult?) {
        val balance = expenses.sumOf { if (it.status == StatusExpense.PENDING) -it.amount else it.amount }
        tvBalanceSummary.text = "Saldo: %.2f€".format(balance)
    }

    private fun buildDebtSummary(debt: Double): String {
        return "Debito totale: %.2f€".format(debt)
    }
}
