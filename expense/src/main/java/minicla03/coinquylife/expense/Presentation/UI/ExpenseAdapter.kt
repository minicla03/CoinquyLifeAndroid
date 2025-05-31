package minicla03.coinquylife.expense.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import minicla03.coinquylife.expense.Data.Response.ExpenseResult
import minicla03.coinquylife.expense.R
import minicla03.coinquylife.expense.Domain.model.Expense
import minicla03.coinquylife.expense.Domain.model.StatusExpense

class ExpenseAdapter(
    private var expenses: MutableList<Expense>,
    private val onMarkPaid: (Expense) -> Unit
) : RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    inner class ExpenseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvAmount: TextView = view.findViewById(R.id.tv_amount)
        val tvDescription: TextView = view.findViewById(R.id.tv_description)
        val tvStatus: TextView = view.findViewById(R.id.tv_da_fare)
        val btnMarkPaid: Button = view.findViewById(R.id.btn_mark_paid)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_expense, parent, false)
        return ExpenseViewHolder(view)
    }

    override fun getItemCount() = expenses.size

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int)
    {
        val expense = expenses[position]
        holder.tvAmount.text = "%.2f€".format(expense.amount)
        holder.tvDescription.text = expense.description

        if (expense.status == StatusExpense.PENDING) {
            holder.tvStatus.text = "Da pagare"
            holder.tvStatus.setTextColor(Color.parseColor("#FFA500"))
            holder.btnMarkPaid.visibility = View.VISIBLE
            holder.btnMarkPaid.setOnClickListener {
                onMarkPaid(expense)
            }
            holder.tvAmount.setTextColor(Color.parseColor("#8A2BE2"))
        } else {
            holder.tvStatus.text = "Saldata"
            holder.tvStatus.setTextColor(Color.parseColor("#BA55D3"))
            holder.btnMarkPaid.visibility = View.GONE
            holder.tvAmount.setTextColor(Color.parseColor("#BA55D3"))
        }
    }

    fun updateExpenses(newExpenses: ExpenseResult?) {
        expenses.clear()
        expenses.addAll(newExpenses)
        notifyDataSetChanged()
    }
}
