package br.com.joaogoes.database.datasource

import br.com.joaogoes.data.datasource.LocalDataSource
import br.com.joaogoes.data.repository.revision.RevisionRepositoryError
import br.com.joaogoes.data.result.Result
import br.com.joaogoes.database.dao.RevisionItemDAO
import br.com.joaogoes.database.mapper.RevisionItemMapper
import br.com.joaogoes.model.RevisionItemModel


class AppLocalDataSource(
    private val revisionItemDAO: RevisionItemDAO,
    private val mapper: RevisionItemMapper
) : LocalDataSource {

    override suspend fun getRevisionItems(): Result<List<RevisionItemModel>, RevisionRepositoryError> =
        try {
            val items = revisionItemDAO.getAll().map { mapper.mapFromEntity(it) }
            Result.Success(items)
        } catch (e: Exception) {
            Result.Error(RevisionRepositoryError.UnknownError("Generic Error"))
        }

    override suspend fun saveRevisionItem(revisionItem: RevisionItemModel): Result<Unit, RevisionRepositoryError> =
        try {
            val item = mapper.mapFromModel(revisionItem)
            if (item.uid == -1) {
                revisionItemDAO.insert(item)
            } else {
                revisionItemDAO.update(item)
            }
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(RevisionRepositoryError.UnknownError("Generic Error"))
        }
}