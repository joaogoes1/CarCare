package br.com.joaogoes.ui.home

import br.com.joaogoes.model.RevisionItemModel

sealed class HomeViewState {
    object Loading : HomeViewState()
    object Error : HomeViewState()
    class Success(val revisionItems: List<RevisionItemModel>) : HomeViewState()
}
