package br.com.joaogoes.data.repository.revision

import br.com.joaogoes.model.RevisionItemModel
import br.com.joaogoes.data.result.Result

interface RevisionRepository {
    fun getRevisionItem(): Result<List<RevisionItemModel>, RevisionRepositoryError>
}