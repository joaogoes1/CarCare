package br.com.joaogoes.data.repository.revision

sealed class RevisionRepositoryError {
    abstract val message: String?

    class IOError(override val message: String? = null): RevisionRepositoryError()
    class UnknownError(override val message: String? = null) : RevisionRepositoryError()
}