package br.com.joaogoes.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "revision_item_entity")
data class RevisionItemEntity(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "initialKilometer") val initialKilometer: Long,
    @ColumnInfo(name = "initial_date") val initialDate: Calendar?,
    @ColumnInfo(name = "target_kilometer") val targetKilometer: Long,
    @ColumnInfo(name = "target_date") val targetDate: Calendar?
)
