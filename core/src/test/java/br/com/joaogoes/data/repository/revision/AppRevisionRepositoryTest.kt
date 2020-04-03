package br.com.joaogoes.data.repository.revision

import br.com.joaogoes.data.datasource.LocalDataSource
import br.com.joaogoes.data.result.Result
import br.com.joaogoes.model.RevisionItemModel
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class AppRevisionRepositoryTest {

    private val dataSource: LocalDataSource = mockk()
    private val repository = AppRevisionRepository(dataSource)

    @Test
    fun getRevisionItems_ifSuccess_returnItemsList() = runBlocking {
        val revisionItem = RevisionItemModel(
            uid = 1,
            itemName = "revisionName",
            currentRevisionKilometer = 100_000,
            nextRevisionKilometer = 100_000
        )

        val expected = Result.Success(listOf(revisionItem))
        coEvery { dataSource.getRevisionItems() } returns expected

        val result = repository.getRevisionItems()

        assertEquals(expected, result)
    }

    @Test
    fun getRevisionItems_ifError_returnUnknownError() = runBlocking {
        val expected = Result.Error(RevisionRepositoryError.UnknownError("Generic Error"))
        coEvery { dataSource.getRevisionItems() } returns expected

        val result = repository.getRevisionItems()

        assertEquals(expected, result)
    }
}
