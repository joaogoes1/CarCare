package br.com.joaogoes.data.usecase

import br.com.joaogoes.data.repository.revision.RevisionRepository
import br.com.joaogoes.data.repository.revision.RevisionRepositoryError
import br.com.joaogoes.data.result.Result
import br.com.joaogoes.data.usecase.SaveRevisionItemUseCase
import br.com.joaogoes.model.RevisionItemModel

class SaveRevisionItem(
    private val revisionRepository: RevisionRepository
): SaveRevisionItemUseCase {
    override suspend fun invoke(revisionItem: RevisionItemModel): Result<Unit, RevisionRepositoryError> =
        revisionRepository.saveRevisionItem(revisionItem)
}