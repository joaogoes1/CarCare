package br.com.joaogoes.data.usecase

import br.com.joaogoes.data.repository.revision.RevisionRepositoryError
import br.com.joaogoes.data.result.Result
import br.com.joaogoes.model.RevisionItemModel

interface GetRevisionItemsUseCase {
    operator fun invoke(): Result<List<RevisionItemModel>, RevisionRepositoryError>
}
