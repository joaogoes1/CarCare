package br.com.joaogoes.data.repository.revision

import br.com.joaogoes.model.RevisionItemModel
import br.com.joaogoes.data.result.Result

interface RevisionRepository {
    suspend fun getRevisionItems(): Result<List<RevisionItemModel>, RevisionRepositoryError>
    suspend fun saveRevisionItem(revisionItem: RevisionItemModel): Result<Unit, RevisionRepositoryError>
}