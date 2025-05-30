package minicla03.coinquylife.houseselection.Presentation.ViewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import minicla03.coinquylife.SelectionHouse.PRESENTATION.ViewModel.SelectHouseViewModel

class SelectHouseViewModelFactory(private val application: Application) : ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T
    {
        if (modelClass.isAssignableFrom(SelectHouseViewModel::class.java))
        {
            return SelectHouseViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
