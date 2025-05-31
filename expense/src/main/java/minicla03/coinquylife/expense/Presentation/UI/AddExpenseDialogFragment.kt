package minicla03.coinquylife.expense.ui

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import minicla03.coinquylife.expense.R
import java.text.SimpleDateFormat
import java.util.*

class AddExpenseDialogFragment(
    private val onAdd: (description: String, amount: Double, date: Date, partecipants: List<String>) -> Unit
) : DialogFragment() {

    private val calendar = Calendar.getInstance()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.add_expense_dialog_fragment_layout, null)

        val etDescription = view.findViewById<EditText>(R.id.et_description)
        val etAmount = view.findViewById<EditText>(R.id.et_amount)
        val btnPickDate = view.findViewById<Button>(R.id.btn_pick_date)
        val tvDate = view.findViewById<EditText>(R.id.tv_date)

        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        tvDate.setText(dateFormat.format(calendar.time))

        btnPickDate.setOnClickListener {
            DatePickerDialog(requireContext(), { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                tvDate.setText(dateFormat.format(calendar.time))
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        return AlertDialog.Builder(requireContext())
            .setTitle("Aggiungi Spesa")
            .setView(view)
            .setPositiveButton("Aggiungi") { _, _ ->
                val description = etDescription.text.toString()
                val amount = etAmount.text.toString().toDoubleOrNull() ?: 0.0
                val date = calendar.time //TODO
                val partecipants = etDescription.text.toString().split(",").map { it.trim() }.filter { it.isNotEmpty() }
                onAdd(description, amount, date, partecipants)
            }
            .setNegativeButton("Annulla", null)
            .create()
    }
}
