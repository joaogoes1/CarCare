package br.com.joaogoes.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.joaogoes.data.usecase.GetRevisionItemsUseCase
import br.com.joaogoes.data.usecase.SaveRevisionItemUseCase
import br.com.joaogoes.model.RevisionItemModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getRevisionItems()
                    .onSuccess { viewState.postValue(HomeViewState.Success(it)) }
                    .onError { error -> viewState.postValue(HomeViewState.Error(error.message)) }

            }
        }
    }

    fun saveRevision(revisionItem: RevisionItemModel) {
        viewState.postValue(HomeViewState.Loading)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                saveRevisionItem(revisionItem)
                    .onSuccess { getRevisions() }
                    .onError { viewState.postValue(HomeViewState.Error(it.message)) }
            }
        }
    }
}