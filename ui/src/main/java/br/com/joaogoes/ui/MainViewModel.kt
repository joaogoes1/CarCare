package br.com.joaogoes.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.joaogoes.data.result.Result
import br.com.joaogoes.data.usecase.GetRevisionItemsUseCase
import br.com.joaogoes.data.usecase.SaveRevisionItemUseCase
import br.com.joaogoes.model.RevisionItemModel
import kotlinx.coroutines.*

class MainViewModel(
    private val getRevisionItems: GetRevisionItemsUseCase,
    private val saveRevisionItem: SaveRevisionItemUseCase
) : ViewModel() {

    val viewState = MutableLiveData<ViewState>()

    sealed class ViewState {
        object Loading : ViewState()
        class Error(val message: String) : ViewState()
        class Success(val revisionItems: List<RevisionItemModel>) : ViewState()
    }

    init {
        getRevisions()
    }

    fun getRevisions() {
        viewState.postValue(ViewState.Loading)
        GlobalScope.launch {
            delay(2000)
            withContext(Dispatchers.IO) {
                when (val result = getRevisionItems()) {
                    is Result.Success ->
                        viewState.postValue(ViewState.Success(result.value))
                    is Result.Error -> viewState.postValue(ViewState.Error(result.value.message))
                }
            }
        }
    }

    fun saveRevision(revisionItem: RevisionItemModel) {
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                saveRevisionItem(revisionItem)
            }
        }
    }
}