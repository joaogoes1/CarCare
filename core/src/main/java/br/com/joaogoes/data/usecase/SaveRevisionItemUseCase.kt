package br.com.joaogoes.data.usecase

import br.com.joaogoes.data.repository.revision.RevisionRepositoryError
import br.com.joaogoes.data.result.Result
import br.com.joaogoes.model.RevisionItemModel

interface SaveRevisionItemUseCase {
    suspend operator fun invoke(revisionItem: RevisionItemModel): Result<Unit, RevisionRepositoryError>
}