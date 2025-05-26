package minicla03.coinquylifek.HOUSE.Presentation.ViewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import minicla03.coinquylifek.APP.TokenManager

import minicla03.coinquylifek.HOUSE.Data.Remote.HouseResult
import minicla03.coinquylifek.HOUSE.Data.Response.HouseStatus
import minicla03.coinquylifek.HOUSE.Domain.UseCase.IJoinHouseUseCase
import minicla03.coinquylifek.HOUSE.Domain.UseCase.INewHouseUseCase
import minicla03.coinquylifek.HOUSE.Domain.UseCase.JoinHouseUseCase
import minicla03.coinquylifek.HOUSE.Domain.UseCase.NewHouseUseCase
import minicla03.coinquylifek.HOUSE.Domain.Repository.ISelectHouseRepository

class SelectHouseViewModel(application: Application) : ViewModel()
{

    private val newHouseUseCase: INewHouseUseCase
    private val joinHouseUseCase: IJoinHouseUseCase

    private val _houseCreationResult = MutableLiveData<HouseResult?>()
    val houseCreationResult: LiveData<HouseResult?> get() = _houseCreationResult

    private val _joinHouseResult = MutableLiveData<HouseResult?>()
    val joinHouseResult: LiveData<HouseResult?> get() = _joinHouseResult

    private val _houseID = MutableLiveData<String?>()
    val houseID: LiveData<String?> get() = _houseID

    init
    {
        val repo: ISelectHouseRepository = HouseSelectionRepository(application)
        val tokenManager = TokenManager(application)
        newHouseUseCase = NewHouseUseCase(repo, tokenManager)
        joinHouseUseCase = JoinHouseUseCase(repo, tokenManager)
    }

    fun createHouse(houseName: String, address: String, token: String)
    {
        if (houseName.isEmpty() || address.isEmpty())
        {
            _houseCreationResult.postValue(HouseResult(HouseStatus.INVALID_INPUT, "Missing house name or address"))
            return
        }

        viewModelScope.launch {
            newHouseUseCase.createHouse(houseName, address, token) { result ->
                _houseCreationResult.postValue(result)
                if (result.status == HouseStatus.HOUSE_CREATED)
                {
                    _houseID.postValue(result.houseId)
                }
            }
        }
    }

    fun joinHouse(houseCode: String, token: String)
    {
        if (houseCode.isEmpty())
        {
            _joinHouseResult.postValue(HouseResult(HouseStatus.INVALID_INPUT, "Missing house code"))
            return
        }

        viewModelScope.launch {
            joinHouseUseCase.joinHouse(houseCode, token) { result ->
                _joinHouseResult.postValue(result)
            }
        }
    }
}
