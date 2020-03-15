package br.com.joaogoes.ui

import androidx.lifecycle.ViewModel
import br.com.joaogoes.data.usecase.GetRevisionItemsUseCase

class MainViewModel(
    private val getRevisionItems: GetRevisionItemsUseCase
) : ViewModel() {

    fun revisionItems() = getRevisionItems().handleResult { list -> list }
}