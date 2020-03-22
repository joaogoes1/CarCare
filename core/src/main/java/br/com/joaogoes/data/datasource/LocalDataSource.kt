package br.com.joaogoes.data.datasource

import br.com.joaogoes.model.RevisionItemModel
import br.com.joaogoes.data.repository.revision.RevisionRepositoryError
import br.com.joaogoes.data.result.Result

interface LocalDataSource {
    suspend fun getRevisionItems(): Result<List<RevisionItemModel>, RevisionRepositoryError>
    suspend fun saveRevisionItem(revisionItem: RevisionItemModel): Result<Unit, RevisionRepositoryError>
}
