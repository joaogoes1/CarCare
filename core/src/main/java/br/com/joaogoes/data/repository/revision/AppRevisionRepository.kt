package br.com.joaogoes.data.repository.revision

import br.com.joaogoes.data.datasource.LocalDataSource
import br.com.joaogoes.model.RevisionItemModel
import br.com.joaogoes.data.result.Result

class AppRevisionRepository(
    private val localDataSource: LocalDataSource
): RevisionRepository {
    override fun getRevisionItem(): Result<List<RevisionItemModel>, RevisionRepositoryError> =
        localDataSource.getRevisionItems()
}