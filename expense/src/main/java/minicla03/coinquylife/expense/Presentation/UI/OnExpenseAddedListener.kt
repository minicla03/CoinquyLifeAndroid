package minicla03.coinquylife.expense.Presentation.UI

import java.util.Date

interface OnExpenseAddedListener
{
    fun onExpenseAdded(
        amount: String?,
        description: String?,
        daFare: Boolean,
        saldata: Boolean,
        data: Date?
    )
}