package br.com.joaogoes.usecases

import br.com.joaogoes.model.RevisionItemModel
import br.com.joaogoes.data.repository.revision.RevisionRepository
import br.com.joaogoes.data.repository.revision.RevisionRepositoryError
import br.com.joaogoes.data.result.Result
import br.com.joaogoes.data.usecase.GetRevisionItemsUseCase

class GetRevisionItems(
    private val repository: RevisionRepository
): GetRevisionItemsUseCase {
    override suspend operator fun invoke(): Result<List<RevisionItemModel>, RevisionRepositoryError> =
        repository.getRevisionItems()
}