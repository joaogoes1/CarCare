package br.com.joaogoes.data.repository.revision

sealed class RevisionRepositoryError {
    object IOError: RevisionRepositoryError()
    object UnknownError: RevisionRepositoryError()
}