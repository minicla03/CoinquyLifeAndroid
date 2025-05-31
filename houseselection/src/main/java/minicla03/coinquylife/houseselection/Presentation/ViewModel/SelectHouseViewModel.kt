package minicla03.coinquylife.houseselection.Presentation.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import minicla03.coinquylife.houseselection.Data.Response.HouseStatus
import minicla03.coinquylife.houseselection.Domain.UseCase.IJoinHouseUseCase
import minicla03.coinquylife.houseselection.Domain.UseCase.INewHouseUseCase

import javax.inject.Inject

@HiltViewModel
class SelectHouseViewModel @Inject constructor(
    private val newHouseUseCase: INewHouseUseCase,
    private val joinHouseUseCase: IJoinHouseUseCase
) : ViewModel()
{
    private val _houseCreationResult = MutableLiveData<HouseStatus?>()
    val houseCreationResult: LiveData<HouseStatus?> get() = _houseCreationResult

    private val _joinHouseResult = MutableLiveData<HouseStatus?>()
    val joinHouseResult: LiveData<HouseStatus?> get() = _joinHouseResult

    private val _houseID = MutableLiveData<String?>()
    val houseID: LiveData<String?> get() = _houseID

    fun createHouse(houseName: String, address: String)
    {
        if (houseName.isEmpty() || address.isEmpty()) {
            _houseCreationResult.postValue(HouseStatus.INVALID_INPUT)
            return
        }

        viewModelScope.launch {
            newHouseUseCase.createHouse(houseName, address) { result ->
                if (result == HouseStatus.LINKED_SUCCES) {
                    _houseID.postValue(result.message)
                }
                _houseCreationResult.postValue(result?.houseStatus)
            }
        }
    }

    fun joinHouse(houseCode: String)
    {
        if (houseCode.isEmpty()){
            _joinHouseResult.postValue(HouseStatus.INVALID_INPUT)
            return
        }

        viewModelScope.launch {
            joinHouseUseCase.joinHouse(houseCode) { result ->
                _joinHouseResult.postValue(result?.houseStatus)
            }
        }
    }
}
