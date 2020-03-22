package br.com.joaogoes.data.repository.revision

import br.com.joaogoes.data.datasource.LocalDataSource
import br.com.joaogoes.data.result.Result
import br.com.joaogoes.model.RevisionItemModel

class AppRevisionRepository(
    private val localDataSource: LocalDataSource
) : RevisionRepository {
    override suspend fun getRevisionItems(): Result<List<RevisionItemModel>, RevisionRepositoryError> =
        localDataSource.getRevisionItems()

    override suspend fun saveRevisionItem(revisionItem: RevisionItemModel):
            Result<Unit, RevisionRepositoryError> = localDataSource.saveRevisionItem(revisionItem)
}