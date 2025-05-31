// DebtsDialogFragment.kt
package minicla03.coinquylife.expense.Presentation.UI

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class DebtsDialogFragment(private val debtSummary: String) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle("Debiti verso persone")
            .setMessage(debtSummary)
            .setPositiveButton("Chiudi", null)
            .create()
    }
}
