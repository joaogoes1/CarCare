package br.com.joaogoes.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.joaogoes.data.result.Result
import br.com.joaogoes.data.usecase.GetRevisionItemsUseCase
import br.com.joaogoes.data.usecase.SaveRevisionItemUseCase
import br.com.joaogoes.model.RevisionItemModel
import kotlinx.coroutines.*

class HomeViewModel(
    private val getRevisionItems: GetRevisionItemsUseCase,
    private val saveRevisionItem: SaveRevisionItemUseCase
) : ViewModel() {

    val viewState = MutableLiveData<HomeViewState>()

    init {
        getRevisions()
    }

    fun getRevisions() {
        viewState.postValue(HomeViewState.Loading)
        GlobalScope.launch {
            delay(2000)
            withContext(Dispatchers.IO) {
                when (val result = getRevisionItems()) {
                    is Result.Success ->
                        viewState.postValue(HomeViewState.Success(result.value))
                    is Result.Error -> viewState.postValue(HomeViewState.Error)
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