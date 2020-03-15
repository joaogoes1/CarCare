package br.com.joaogoes.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.com.joaogoes.database.entity.RevisionItemEntity

@Dao
interface RevisionItemDAO {
    @Query("SELECT * FROM revision_item_entity")
    fun getAll(): List<RevisionItemEntity>

    @Query("SELECT * FROM revision_item_entity WHERE name LIKE :name LIMIT 1")
    fun findByName(name: String): RevisionItemEntity

    @Insert
    fun insert(revisionItem: RevisionItemEntity)

    @Delete
    fun delete(user: RevisionItemEntity)
}